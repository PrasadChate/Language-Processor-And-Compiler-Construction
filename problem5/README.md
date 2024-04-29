# Problem 5
Write a program to evaluate a given variable name using YACC specification.
SAMPLE INPUT
1) pune
2) PUNE
3) Pune1
4) pUNE_2
follow the steps to run the file of this task:

## To compile and run this Lex specification:

Save the Lex code in a file named 5_var.l
Save the YACC code in a file named 5_var.y

Following commands to run code

```bash
$ flex 5_var.l
$ yacc -d 5_var.y
$ gcc lex.yy.c y.tab.c -o 5_var -lfl
$ ./5_var

```
