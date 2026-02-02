# 🖥️ Virtual Machine

This project implements a register-based Virtual Machine (VM) in Java, along with a custom Instruction Set
Architecture (ISA) and a two-pass assembler. The system models a simplified CPU architecture using a unified memory
design and demonstrates the full compilation and execution pipeline from assembly source code to machine-level
execution.

## Overview

This VM consists of:

- 4 General Purpose Registers (R0–R3)
- Instruction Pointer (IP)
- RAM size of 256 words (1024 bytes)
- Custom Instruction Set Architecture (ISA)
- Two-pass Assembler
- Static `.data` memory allocation

## Architecture Design

![Architecture](src/main/resources/assets/architecture_diagram.png)

## Instruction Set

### Data Movement

| Instruction    | Description                                     |
|----------------|-------------------------------------------------|
| `ldc Rn value` | Load constant value into register `Rn`          |
| `ldr Rn Rm`    | Load into `Rn` from memory address in `Rm`      |
| `str Rn Rm`    | Store value of `Rn` into memory address in `Rm` |
| `cpy Rn Rm`    | Copy value from `Rm` into `Rn`                  |

### Arithmetic

| Instruction | Description        |
|-------------|--------------------|
| `add Rn Rm` | Add registers      |
| `sub Rn Rm` | Subtract registers |

### Control Flow

| Instruction     | Description                |
|-----------------|----------------------------|
| `je Rn @label`  | Jump to label if `Rn == 0` |
| `jne Rn @label` | Jump to label if `Rn != 0` |
| `hlt`           | Halt execution             |

### I/O

| Instruction | Description                               |
|-------------|-------------------------------------------|
| `prr Rn`    | Print register value                      |
| `prm Rn`    | Print memory value at address in register |
| `prc Rn`    | Print register value as ASCII character   |

## Execution Flow

1. Write the program in the custom assembly language
2. The assembler then:
    - Cleans and parses the program
    - Resolves labels
    - Allocates `.data` memory
    - Encodes instructions into machine code

3. Machine code is loaded into RAM
4. The VM repeatedly reads the next instruction from memory, decodes its opcode and operands, and executes the
   corresponding operation until it encounters a halt instruction

## Acknowledgement

This project was inspired by and extended from the architectural concepts explained
in  [Software Design by Example](https://third-bit.com/sdxjs/) by Greg Wilson.