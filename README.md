# Language-Processor-And-Compiler-Construction
<br>Codes Are Written In Java
Tasks:-
<br>
		1.	"Write a program to generate Symbol & Literal table of a two-pass Assembler for the given Assembly language source code.<br>
		            START 300
		<br>READ M
		    <br>              READ N
		        <br>   MOVER AREG, =’51'
		            <br>    MOVER BREG, =’61’
		            <br>ADD AREG, BREG
	<br>	LOOP        MOVER CREG, M  
	<br>	                ADD  CREG, ='11'
	<br>	COMP CREG, N
	<br>	                BC LT, LOOP
	<br>	NEXT        SUB AREG, ='11'
	<br>	COMP AREG, N 
	<br>	BC  GT, NEXT
	<br>	STOP
	<br>	M                DS        1
	<br>	N                DS        1
	<br>	END
	<br>	"
<br>#2 .	Build a lexer, using LEX, for finding whether a document is of a given TOPIC -Medical (Hint: search for predecided key words, (related to that TOPIC), in the document and count them to decide whether they are enough to declare whether a document is of the topic or not)
<br>follow the steps to run the file of this task:
<br><br>To compile and run this Lex specification:

<br>Save the Lex code in a file named 2 medical.l.
<br>Use Flex to generate the lexer:
1)flex 2 medical.l
2)Compile the generated C code:gcc lex.yy.c -o 2 medical -lfl
3)Run the executable: ./2 medical < input_document.txt

<br>#3.	Write a program to generate Symbol , Literal table &Pool table of a two-pass Assembler for the given Assembly language source code.
<br>	INPUT/CODE
<br>	START 100
<br>	READ A
<br>	MOVER AREG, ='1'
<br>	MOVEM AREG, B
<br>	MOVER BREG, ='6'
<br>	ADD AREG, BREG
<br>	COMP AREG, A
<br>	BC GT, LAST
<br>	LTORG
<br>	NEXT SUB AREG, ='1'
<br>	MOVER CREG, B
<br>	ADD CREG, ='8'
<br>	MOVEM CREG, B
<br>	PRINT B
<br>	LAST STOP
<br>	A DS 1
<br>	B DS 1
<br>	END
<br>#4.	Write a program to generateMNT, MDT & Intermediate code of a two-pass Macro processor
<br>	INPUT/CODE
<br>	LOAD J
<br>	STORE M
<br>	MACRO EST
<br>	LOAD e
<br>	ADD d
<br>	MEND
<br>	LOAD S
<br>	MACRO SUB4 ABC
<br>	LOAD U
<br>	STORE ABC
<br>	MEND
  <br>LOAD P
<br>ADD V
<br>MACRO ADD7 P4, P5, P6
<br>LOAD P5
<br>SUB4 XYZ
<br>SUB 8
<br>SUB 2
<br>STORE P4
<br>STORE P6
<br>MEND
<br>EST
<br>ADD7 C4, C5, C6
<br>SUB4 z
<br>END
<br>#5.	
	".Write a program to evaluate a given variable name using YACC specification.
<br> SAMPLE INPUT
 1) pune
 2) PUNE
 3) Pune1
 4) pUNE_2"
<br> To run the 5th task files follow the following steps:
<br>1.Install MinGW:
<br>During installation, select the option to install MSYS (Minimal SYStem), which provides a Unix-like shell environment for Windows.
<br>2.Install Flex:
<br>Write YACC and Lex Specifications:
Save the YACC code in a file named 5 variable.y.
Save the Lex code in a file named 5 variable.l
<br>Compile YACC Specification:
Open the MSYS shell (MinGW Shell) from the Start menu.
Navigate to the directory containing variable.y.
Run the following command:-
<br>(a)yacc -d 5 variable.y
<br>(b)flex 5 variable.l
<br>(c)gcc y.tab.c lex.yy.c -o 5 variable.exe -lfl
<br>(d)./5 variable.exe
