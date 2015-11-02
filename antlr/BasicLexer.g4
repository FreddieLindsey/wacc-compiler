lexer grammar BasicLexer;

// TODO: EOL, EOF?
WS
    : (' ' | '\t' | '\r' | '\n' | '\f')+ -> skip
    ;

//keywords
BEGIN  : 'begin'   ;
END    : 'end'     ;

IS     : 'is'      ;
SKIP   : 'skip'    ;
READ   : 'read'    ;
FREE   : 'free'    ;

RETURN : 'return'  ;
EXIT   : 'exit'    ;

PRINT  : 'print'   ;
PRINTLN: 'println' ;

IF     : 'if'      ;
THEN   : 'then'    ;
ELSE   : 'else'    ;
FI     : 'fi'      ;

WHILE  : 'while'   ;
DO     : 'do'      ;
DONE   : 'done'    ;

NEWPAIR: 'newpair' ;
CALL   : 'call'    ;

FST    : 'fst'     ;
SND    : 'snd'     ;

//types
INT    : 'int'     ;
BOOL   : 'bool'    ;
CHAR   : 'char'    ;
STRING : 'string'  ;

PAIR   : 'pair'    ;

//unary operators
NOT : '!';
// NEG : '-' ; Duplicated symbol, semantic check?
LEN    : 'len'     ;
ORD    : 'ord'     ;
CHR    : 'chr'     ;

//binary operators
MUL : '*' ;
DIV : '/' ;
MOD : '%' ;
ADD : '+' ;
SUB : '-' ;
G   : '>' ;
GE  : '>=' ;
L   : '<' ;
LE  : '<=' ;
EQ  : '==' ;
NEQ : '!=' ;
AND : '&&' ;
OR  : '||' ;

ASSIGN : '=';

TRUE   : 'true'    ;
FALSE  : 'false'   ;

NULL   : 'null'    ;

NEWLINE: '\r'? '\n' -> skip;

//numbers
fragment DIGIT : [0-9] ; 
INTEGER : DIGIT+ ;

//brackets
OPEN_PARENTHESES : '(' ;
CLOSE_PARENTHESES : ')' ;
OPEN_BRACKET : '[' ;
CLOSE_BRACKET : ']' ;

//identifiers
IDENT : ( '_' | 'a'..'z' | 'A'..'Z' ) 
    ( '_' | 'a'..'z' | 'A'..'Z' | '0'..'9' )* ;

//punctuation
COMMA : ','  ;
APOST : '\'' ;
QUOTE : '"' ;
HASH  : '#'  ;
SEMI  : ';'  ;
ESC   : '\\' ;

fragment ESCAPED_CHARS : ('0' | 'b' | 't' | 'n' | 'f' | 'r' | '\'' | '"' | '\\') ;
ESCAPED_CHAR : ESC ESCAPED_CHARS;

STR : QUOTE ~('\r' | '\n')* QUOTE ;

//CHARAC to differentiate from CHAR type on line ~36

CHARAC
    :   APOST SingleCharacter APOST
    |   APOST ESCAPED_CHAR APOST
    ;

fragment
SingleCharacter
    :   ~['\\]
    ;

//CHAR_LITER : ''' CHAR ''' ;


COMMENT
    :   '#' ~[\r\n]* '\r'? '\n' -> skip
    ;


