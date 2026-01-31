package com.aarush.vm.assembler;

import com.aarush.vm.architecture.Instruction;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.aarush.vm.architecture.Architecture.OPS;
import static com.aarush.vm.architecture.Architecture.OP_SHIFT;

public class Assembler {

    public String assemble(List<String> lines) {
        lines = getLines(lines);
        Map<String, Integer> labels = findLabels(lines);

        List<String> instructions = new ArrayList<>();
        for (String line : lines) {
            if (!isLabel(line)) instructions.add(line);
        }

        List<Integer> compiled = new ArrayList<>();
        for (String instruction : instructions) {
            compiled.add(compile(instruction, labels));
        }
        return generateProgramText(compiled);
    }

    private List<String> getLines(List<String> lines) {
        List<String> cleaned = new ArrayList<>();
        for (String line : lines) {
            line = line.split(";")[0].trim(); // Remove comment
            if (!line.isEmpty()) cleaned.add(line);
        }
        return cleaned;
    }

    private Map<String, Integer> findLabels(List<String> lines) {
        Map<String, Integer> res = new HashMap<>();
        int pos = 0;
        for (String line : lines) {
            if (isLabel(line)) {
                String label = line.substring(0, line.length() - 1).trim();
                res.put(label, pos);
            } else pos++;
        }
        return res;
    }

    private boolean isLabel(String line) {
        return line.endsWith(":");
    }

    private int compile(String instruction, Map<String, Integer> labels) {
        String[] tokens = instruction.split("\\s+");
        String op = tokens[0];
        String[] args = new String[tokens.length - 1];
        for (int i = 1; i < tokens.length; i++) {
            args[i - 1] = tokens[i];
        }

        Instruction instructionObj = OPS.get(op);
        String fmt = instructionObj.fmt();
        int opcode = instructionObj.opcode();

        return switch (fmt) {
            case "--" -> combine(opcode);
            case "r-" -> combine(reg(args[0]), opcode);
            case "rr" -> combine(reg(args[1]), reg(args[0]), opcode);
            case "rv" -> combine(val(args[1], labels), reg(args[0]), opcode);
            default -> throw new RuntimeException("Invalid format");
        };
    }

    private String generateProgramText(List<Integer> program) {
        StringBuilder res = new StringBuilder();
        for (int word : program) {
            res.append(word).append("\n");
        }
        return res.toString();
    }

    private int combine(int... args) {
        int res = 0;
        for (int arg : args) {
            res <<= OP_SHIFT;
            res |= arg;
        }
        return res;
    }

    private int reg(String token) {
        return Integer.parseInt(token.substring(1));
    }

    private int val(String token, Map<String, Integer> labels) {
        if (token.charAt(0) != '@') return Integer.parseInt(token);
        String label = token.substring(1);
        return labels.get(label);
    }
}
