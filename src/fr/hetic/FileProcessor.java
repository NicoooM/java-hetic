package fr.hetic;

import java.io.*;
import java.nio.file.*;
import java.util.stream.*;


public class FileProcessor {

    public void processDirectory(File folder) {
        try (Stream<Path> paths = Files.walk(folder.toPath())) {
            paths.filter(Files::isRegularFile)
                .filter(path -> path.toString().endsWith(".op"))
                .forEach(path -> processFile(path.toFile()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void processFile(File file) {
        Path path = file.toPath();
        Path resultPath = Paths.get(file.getParent(), file.getName().replaceFirst("[.][^.]+$", "") + ".res");

        try (Stream<String> lines = Files.lines(path);
             PrintWriter writer = new PrintWriter(Files.newBufferedWriter(resultPath))) {
            lines.map(this::processLine) 
                 .forEach(writer::println); 
        } catch (IOException e) {
            System.err.println("Error processing file: " + file.getName());
            e.printStackTrace();
        }
    }

    private String processLine(String line) {
        String[] parts = line.split(" ");
        if (parts.length == 3) {
            try {
                int num1 = Integer.parseInt(parts[0]);
                int num2 = Integer.parseInt(parts[1]);
                String op = parts[2];
                Operation operation = OperationFactory.getOperation(op);
                return String.valueOf(operation.execute(num1, num2));
            } catch (Exception e) {
                return "Error: " + e.getMessage();
            }
        } else {
            return "Error: Incorrect format";
        }
    }
}
