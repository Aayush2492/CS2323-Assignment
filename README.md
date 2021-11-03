# Assignment 1- CS2323

`sample_data/test_assembler/asm_files` contains the asm files which cover all the instructions in the table 2.1. On running the assembler you will get the binary and hex codes of each instruction in `sample_data/test_assembler/asm_to_bin_hex_files`

`sample_data/test_deassembler/bin_files` contains the binary bits which we got from previous folder. On running the deassembler you will get the asm format of each instruction in `sample_data/test_deassembler/bin_to_asm`

Verify that content of `sample_data/test_assembler/asm_files` and `sample_data/test_deassembler/bin_to_asm` match.

### Requirements

Make sure the java compiler and SDK are installed.

### Execution

Compile the code using the following command in the terminal:

```
make
```

Then execute the assembler java file using,

```
make assemble
```

and then execute the deassembler java file using,

```
make deassemble
```

### Clean

To delete the \*.class files, run:

```
make clean
```
