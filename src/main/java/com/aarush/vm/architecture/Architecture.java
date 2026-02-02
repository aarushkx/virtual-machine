package com.aarush.vm.architecture;

import java.util.Map;

public class Architecture {

    public static final int REG_COUNT = 4;      // Number of general-purpose registers
    public static final int RAM_SIZE = 256;     // Number of words available in RAM
    public static final int BYTES_PER_WORD = 4; // Java int (32 bits = 4 bytes)
    public static final int TOTAL_MEMORY_SIZE =
            RAM_SIZE * BYTES_PER_WORD;          // 8192 bits (1024 bytes)

    public static final Map<String, Instruction> OPS = Map.ofEntries(
            Map.entry("hlt", new Instruction(0x1, "--")),   // Halt program
            Map.entry("ldc", new Instruction(0x2, "rv")),   // Load constant value
            Map.entry("ldr", new Instruction(0x3, "rr")),   // Load register
            Map.entry("cpy", new Instruction(0x4, "rr")),   // Copy register
            Map.entry("str", new Instruction(0x5, "rr")),   // Store register
            Map.entry("add", new Instruction(0x6, "rr")),   // Add
            Map.entry("sub", new Instruction(0x7, "rr")),   // Subtract
            Map.entry("je", new Instruction(0x8, "rv")),    // Jump if equal
            Map.entry("jne", new Instruction(0x9, "rv")),   // Jump if not equal
            Map.entry("prr", new Instruction(0xA, "r-")),   // Print register
            Map.entry("prm", new Instruction(0xB, "r-")),   // Print memory
            Map.entry("prc", new Instruction(0xC, "r-"))    // Print character
    );

    public static final int OP_MASK = 0xFF; // Used to extract the lowest 8 bits (1 byte)
    public static final int OP_SHIFT = 8;   // Number of bits to shift when packing/unpacking instruction fields
    public static final int OP_WIDTH = 6;   // Width used for formatting opcode display
}
