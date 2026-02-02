package com.aarush.vm.main;

import com.aarush.vm.assembler.Assembler;
import com.aarush.vm.helper.FileHandler;

import java.io.IOException;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {
        /*
         * To run a program:
         * 1. Write your assembly code (.asm) in the appropriate resources folder (src/main/resources/test)
         * 2. Change the 'asmFileName' variable below to match your file name (without extension)
         */
        String asmFileName = "test4";
        List<String> lines = FileHandler.loadTestResource(asmFileName + ".asm");

        Assembler assembler = new Assembler();
        String programText = assembler.assemble(lines);
        String objFileName = asmFileName + ".o";

        FileHandler.writeOutput(programText, objFileName);

        System.out.println("Program assembled successfully");
        System.out.println("\nRunning program...\n");
        System.out.println("PROGRAM OUTPUT:");

        List<Integer> program = FileHandler.loadOutputMachineCode(objFileName);

        VirtualMachine vm = new VirtualMachine();
        vm.init(program);
        vm.run();
    }
}