%{
#include "y.tab.h"
%}

%%
[a-zA-Z][a-zA-Z0-9_]{0,19} { yylval = strdup(yytext); return IDENTIFIER; }
.|\n { return yytext[0]; }
%%

int yywrap() {
    return 1;
}
