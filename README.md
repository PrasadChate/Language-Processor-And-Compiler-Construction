# Language-Processor-And-Compiler-Construction
Tasks:-
		1.	"Write a program to generate Symbol & Literal table of a two-pass Assembler for the given Assembly language source code.
		            START 300
		READ M
		                  READ N
		           MOVER AREG, =’51'
		                MOVER BREG, =’61’
		            ADD AREG, BREG
		LOOP        MOVER CREG, M  
		                ADD  CREG, ='11'
		COMP CREG, N
		                BC LT, LOOP
		NEXT        SUB AREG, ='11'
		COMP AREG, N 
		BC  GT, NEXT
		STOP
		M                DS        1
		N                DS        1
		END
		"
#2.	Build a lexer, using LEX, for finding whether a document is of a given TOPIC -Medical (Hint: search for predecided key words, (related to that TOPIC), in the document and count them to decide whether they are enough to declare whether a document is of the topic or not)
#3.	Write a program to generate Symbol , Literal table &Pool table of a two-pass Assembler for the given Assembly language source code.
	INPUT/CODE
	START 100
	READ A
	MOVER AREG, ='1'
	MOVEM AREG, B
	MOVER BREG, ='6'
	ADD AREG, BREG
	COMP AREG, A
	BC GT, LAST
	LTORG
	NEXT SUB AREG, ='1'
	MOVER CREG, B
	ADD CREG, ='8'
	MOVEM CREG, B
	PRINT B
	LAST STOP
	A DS 1
	B DS 1
	END
#4.	Write a program to generateMNT, MDT & Intermediate code of a two-pass Macro processor
	INPUT/CODE
	LOAD J
	STORE M
	MACRO EST
	LOAD e
	ADD d
	MEND
	LOAD S
	MACRO SUB4 ABC
	LOAD U
	STORE ABC
	MEND
  LOAD P
ADD V
MACRO ADD7 P4, P5, P6
LOAD P5
SUB4 XYZ
SUB 8
SUB 2
STORE P4
STORE P6
MEND
EST
ADD7 C4, C5, C6
SUB4 z
END
#5.	
	".Write a program to evaluate a given variable name using YACC specification.
 SAMPLE INPUT
 1) pune
 2) PUNE
 3) Pune1
 4) pUNE_2"


