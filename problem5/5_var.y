%{
#include<stdio.h>
#include "y.tab.h"
%}
%token BUILTIN UD WS ID OPEN_SQ CLOSE_SQ EQ NEW SC COMMA DIGIT 
%%
start :  varlist WS varlist  {printf(" NOT Valid Declaration \n\n");} 
        |  varlist UD DIGIT  {printf("Valid Declaration \n\n");}
        |  varlist  {printf("Valid Declaration \n\n");}
        |  varlist UD varlist {printf("Valid Declaration \n\n");}
        | varlist : varlist COMMA ID | ID;
%%

int yywrap()
{ return 1;
}

int main()
{

    while(1){

        yyparse();
    }
}

int yyerror(char *s)
{
	fprintf(stderr,"%s\n",s);
	return 1;
}
