JFLAGS = -g
JC = javac

.SUFFIXES: .java .class

.java.class:
	$(JC) $(JFLAGS) $*.java

CLASSES = \
		  Assembler.java \
		  Instruction.java \
		  RFormatInstruction.java \
		  IFormatInstruction.java \
		  JFormatInstruction.java \

default: classes

classes: $(CLASSES:.java=.class)

clean:
	$(RM) *.class
	
run:
	java Assembler