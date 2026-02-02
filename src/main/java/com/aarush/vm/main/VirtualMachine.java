package com.aarush.vm.main;

import java.util.ArrayList;
import java.util.List;

import static com.aarush.vm.architecture.Architecture.BYTES_PER_WORD;
import static com.aarush.vm.architecture.Architecture.OPS;
import static com.aarush.vm.architecture.Architecture.OP_MASK;
import static com.aarush.vm.architecture.Architecture.OP_SHIFT;
import static com.aarush.vm.architecture.Architecture.RAM_SIZE;
import static com.aarush.vm.architecture.Architecture.REG_COUNT;
import static com.aarush.vm.architecture.Architecture.TOTAL_MEMORY_SIZE;

public class VirtualMachine {

    private int[] ram;
    private int[] regs;
    private int ip;
    private String prompt;

    public VirtualMachine() {
        this.init(new ArrayList<>());
        this.prompt = ">>";
    }

    public void init(List<Integer> program) {
        if (program.size() > RAM_SIZE) {
            int programSizeInBytes = program.size() * BYTES_PER_WORD;
            throw new RuntimeException(
                    "Program size " + programSizeInBytes + " bytes exceeds available RAM capacity " + TOTAL_MEMORY_SIZE + " bytes"
            );
        }

        ram = new int[RAM_SIZE];
        regs = new int[REG_COUNT];

        for (int i = 0; i < RAM_SIZE; i++) {
            if (i < program.size()) ram[i] = program.get(i);
            else ram[i] = 0;
        }
        ip = 0;
        regs = new int[REG_COUNT];
        for (int i = 0; i < regs.length; i++) {
            regs[i] = 0;
        }
    }

    public int[] fetch() {
        int instruction = ram[ip];
        ip++;

        int opcode = instruction & OP_MASK;
        instruction >>= OP_SHIFT;

        int arg0 = instruction & OP_MASK;
        instruction >>= OP_SHIFT;

        int arg1 = instruction & OP_MASK;
        return new int[]{opcode, arg0, arg1};
    }

    public void run() {
        boolean isRunning = true;
        while (isRunning) {
            int[] instructions = fetch();
            int opcode = instructions[0];
            int arg0 = instructions[1];
            int arg1 = instructions[2];

            if (opcode == OPS.get("hlt").opcode()) isRunning = false;
            else if (opcode == OPS.get("ldc").opcode()) regs[arg0] = arg1;
            else if (opcode == OPS.get("ldr").opcode()) regs[arg0] = ram[regs[arg1]];
            else if (opcode == OPS.get("cpy").opcode()) regs[arg0] = regs[arg1];
            else if (opcode == OPS.get("str").opcode()) ram[regs[arg1]] = regs[arg0];
            else if (opcode == OPS.get("add").opcode()) regs[arg0] += regs[arg1];
            else if (opcode == OPS.get("sub").opcode()) regs[arg0] -= regs[arg1];
            else if (opcode == OPS.get("je").opcode()) ip = regs[arg0] == 0 ? arg1 : ip;
            else if (opcode == OPS.get("jne").opcode()) ip = regs[arg0] != 0 ? arg1 : ip;
            else if (opcode == OPS.get("prr").opcode()) System.out.println(regs[arg0]);
            else if (opcode == OPS.get("prm").opcode()) System.out.println(ram[regs[arg0]]);
            else if (opcode == OPS.get("prc").opcode()) System.out.print((char) regs[arg0]);
            else throw new RuntimeException("Unknown opcode: " + opcode);
        }
    }
}
