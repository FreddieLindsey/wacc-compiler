parser grammar BasicParser;

options {
  tokenVocab=BasicLexer;
}

program : BEGIN func* stat END ;

func    : type ident OPEN_PARENTHESES paramlist? CLOSE_PARENTHESES
		  IS stat END ;

paramlist : param (COMMA param)+ ;

param : type ident ;

stat :	  SKIP
		| type ident EQ assignrhs
		| assignlhs EQ assignrhs
		| READ assignrhs
		| FREE expr
		| RETURN expr
		| EXIT expr
		| PRINT expr
		| PRINTLN expr
		| IF expr THEN stat ELSE stat FI
		| WHILE expr DO stat DONE
		| BEGIN stat END
		| stat SEMI stat ;

assignlhs :   ident
			| arrayelem
			| pairelem ;

assignrhs :   expr
			| arrayliter
			| NEWPAIR OPEN_PARENTHESES expr COMMA expr CLOSE_PARENTHESES
			| pairelem
			| CALL ident OPEN_PARENTHESES arglist? CLOSE_PARENTHESES ;

arglist : expr (COMMA expr)* ;

pairelem :   FST expr
		   | SND expr ;

type :   basetype
	   | arraytype
	   | pairtype ;

basetype : INT
		 | BOOL
		 | CHAR
		 | STRING ;

arraytype : (basetype | pairtype) OPEN_BRACKET CLOSE_BRACKET 
		  | arraytype OPEN_BRACKET CLOSE_BRACKET;

pairtype : PAIR OPEN_PARENTHESES pairelemtype COMMA pairelemtype CLOSE_PARENTHESES ;

pairelemtype :   basetype
			   | arraytype
			   | PAIR ;

expr : intliter
	   | boolliter
	   | charliter
	   | strliter
	   | pairliter
	   | ident
	   | arrayelem
	   | unaryoper expr
	   | expr binaryOper expr
	   | OPEN_PARENTHESES expr CLOSE_PARENTHESES ;

unaryoper : NOT | SUB | LEN | ORD | CHR ;


binaryOper :  MUL 
			| DIV
			| MOD
			| ADD
			| SUB
			| G  
			| GE  
			| L  
			| LE  
			| EQ  
			| NEQ 
			| AND 
			| OR ;

ident : IDENT ;

arrayelem : ident (OPEN_BRACKET expr CLOSE_BRACKET)+ ;

intliter : intsign INTEGER ;

// digit, lexer or parser?

intsign : ADD | SUB ;

boolliter : TRUE | FALSE ;

charliter : APOST CHAR APOST ;

strliter : QUOTE CHAR* QUOTE ;

//character must be in lexer because has escaped char rule!
character : APOST (CHARAC | ESC ESCAPED_CHAR) APOST;

arrayliter : OPEN_BRACKET (expr (COMMA expr)*)? CLOSE_BRACKET ;

pairliter : NULL ;



//binaryOper : PLUS | MINUS ;

//expr: expr binaryOper expr
//| INTEGER
//| OPEN_PARENTHESES expr CLOSE_PARENTHESES
//;

// EOF indicates that the program must consume to the end of the input.
//prog: (expr)*  EOF ;
