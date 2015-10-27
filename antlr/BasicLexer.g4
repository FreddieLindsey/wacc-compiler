lexer grammar BasicLexer;

//operators
MUL : '*' ;
DIV : '/' ;
ADD : '+' ;
SUB : '-' ;

//brackets
OPEN_PARENTHESES : '(' ;
CLOSE_PARENTHESES : ')' ;

//numbers
fragment DIGIT : '0'..'9' ; 

INTEGER: DIGIT+ ;





