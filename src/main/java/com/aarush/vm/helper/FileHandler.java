package com.aarush.vm.helper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class FileHandler {

    public static List<String> loadTestResource(String path) throws IOException {
        InputStream is = FileHandler.class.getClassLoader().getResourceAsStream("test/" + path);
        if (is == null) throw new RuntimeException("Could not find resource: " + path);

        List<String> lines = new ArrayList<>();
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader br = new BufferedReader(isr);

        String line;
        while ((line = br.readLine()) != null) {
            lines.add(line);
        }
        br.close();
        return lines;
    }

    public static void writeOutput(String programText, String fileName) throws IOException {
        Path outputDir = Path.of("output");
        Files.createDirectories(outputDir);

        Path outputFile = outputDir.resolve(fileName);
        Files.writeString(outputFile, programText);

        System.out.println("Output written to: " + outputFile.toAbsolutePath());
    }

    public static List<Integer> loadOutputMachineCode(String fileName) throws IOException {
        Path file = Path.of("output").resolve(fileName);
        List<String> lines = Files.readAllLines(file);
        List<Integer> program = new ArrayList<>();
        for (String line : lines) {
            if (!line.isBlank()) {
                program.add(Integer.parseInt(line.trim()));
            }
        }
        return program;
    }
}
