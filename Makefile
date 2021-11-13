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
	rm -rf sample_data/test_assembler/asm_to_bin_hex_files/prog{1..5}bin_hex.txt && rm -rf sample_data/test_deassembler/bin_to_asm/prog{1..5}asm.txt

classes: $(CLASSES:.java=.class)

#assemble:
#	for number in 1 2 3 4 5 ; do \
#    	java Assembler $$number ; \
#	done

assemble:
	java Assembler 1 && java Assembler 2 && java Assembler 3 && java Assembler 4 && java Assembler 5

deassemble:
	java Deassembler 1 && java Deassembler 2 && java Deassembler 3 && java Deassembler 4 && java Deassembler 5

clean:
	$(RM) *.class && rm -rf sample_data/test_assembler/asm_to_bin_hex_files/prog{1..5}bin_hex.txt && rm -rf sample_data/test_deassembler/bin_to_asm/prog{1..5}asm.txt