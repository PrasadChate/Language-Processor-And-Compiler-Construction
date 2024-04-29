# Problem 2
Build a lexer, using LEX, for finding whether a document is of a given TOPIC -Medical (Hint: search for predecided key words, (related to that TOPIC), in the document and count them to decide whether they are enough to declare whether a document is of the topic or not)
follow the steps to run the file of this task:

## To compile and run this Lex specification:

Save the Lex code in a file named 2_medical.l
Use Flex to generate the lexer

```bash
$ flex 2_medical.l
$ gcc lex.yy.c -o 2_medical -lfl
$ ./2_medical < input_document.txt

```
Replace input_document.txt with the path to the document you want to analyze
