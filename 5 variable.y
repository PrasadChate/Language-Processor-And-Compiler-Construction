%{
#include <stdio.h>
%}

%token IDENTIFIER

%%

start: IDENTIFIER { printf("Valid variable name: %s\n", $1); }
      | error '\n' { printf("Invalid variable name\n"); }
      ;

IDENTIFIER: [a-zA-Z][a-zA-Z0-9_]{0,19}

%%

int main() {
    yyparse();
    return 0;
}

int yyerror(char *s) {
    printf("Syntax Error: %s\n", s);
    return 0;
}
