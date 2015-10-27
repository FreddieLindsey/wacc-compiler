lexer grammar BasicLexer;

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

//brackets
OPEN_PARENTHESES : '(' ;
CLOSE_PARENTHESES : ')' ;

//numbers
fragment DIGIT : '0'..'9' ; 

INTEGER : DIGIT+ ;

//identifiers
IDENT : ( '_' | 'a'..'z' | 'A'..'Z' ) 
    ( '_' | 'a'..'z' | 'A'..'Z' | '0'..'9' )* ;



