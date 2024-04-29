%{
#include<stdio.h>
#include "y.tab.h"
%}

%%
new return NEW;
"[" return OPEN_SQ;
"]" return CLOSE_SQ;
"=" return EQ;
"," return COMMA;
"_" return UD;
(["\t"])+ return WS;
[a-zA-Z]+[a-zA-Z0-9]* return ID;
[0-9]+ return DIGIT;
\n return 0;
%%

