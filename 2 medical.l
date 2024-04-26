%{
#include <stdio.h>
int medical_count = 0;
%}

%%

"medicine"|"medical"|"health" { medical_count++; }
"doctor"|"physician"|"nurse" { medical_count++; }
"hospital"|"clinic"|"patient" { medical_count++; }
"diagnosis"|"treatment"|"prescription" { medical_count++; }
"vaccine"|"vaccination"|"immunization" { medical_count++; }

.|\n { /* Ignore other characters */ }

%%

int main() {
    yylex();
    
    if (medical_count >= 3) {
        printf("The document is related to the Medical topic.\n");
    } else {
        printf("The document is not related to the Medical topic.\n");
    }

    return 0;
}
