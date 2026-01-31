package com.aarush.vm.main;

import com.aarush.vm.assembler.Assembler;
import com.aarush.vm.helper.FileHandler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {
        List<String> lines = FileHandler.loadTestResource("test.asm");

        Assembler assembler = new Assembler();
        String programText = assembler.assemble(lines);

        FileHandler.writeOutput(programText, "test.o");

        System.out.println("Program assembled successfully");
        System.out.println("\nRunning program...\n");
        System.out.println("PROGRAM OUTPUT:");

        // Convert text to integer (machine code) program
        List<Integer> program = new ArrayList<>();
        String[] programLines = programText.split("\\R");
        for (String line : programLines) {
            program.add(Integer.parseInt(line));
        }

        VirtualMachine vm = new VirtualMachine();
        vm.init(program);
        vm.run();
    }
}
