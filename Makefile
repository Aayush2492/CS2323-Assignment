JFLAGS = -g
JC = javac

.SUFFIXES: .java .class

.java.class:
	$(JC) $(JFLAGS) $*.java

CLASSES = \
		  Assembler.java \
		  Deassembler.java \
		  Instruction.java \
		  RFormatInstruction.java \
		  IFormatInstruction.java \
		  JFormatInstruction.java \

default: classes

classes: $(CLASSES:.java=.class)

clean:
	$(RM) *.class
	
#assemble:
#	for number in 1 2 3 4 5 ; do \
#    	java Assembler $$number ; \
#	done

assemble:
	java Assembler 1 && java Assembler 2 && java Assembler 3 && java Assembler 4 && java Assembler 5

deassemble:
	java Deassembler