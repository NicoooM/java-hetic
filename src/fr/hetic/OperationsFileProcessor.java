package fr.hetic;

import java.io.*;

public class OperationsFileProcessor {
    public static void main(String[] args) {
        File folder = new File("./fr/hetic/operations");

        if (!folder.exists()) {
            System.out.println("Directory does not exist");
            return;
        }

        processDirectory(folder);
    }

    private static void processDirectory(File folder) {
        File[] listOfFiles = folder.listFiles();
        if (listOfFiles == null) {
            return;
        }

        for (File file : listOfFiles) {
            if (file.isFile() && file.getName().endsWith(".op")) {
                processFile(file);
            } else if (file.isDirectory()) {
                processDirectory(file); // Recursively process subdirectories
            }
        }
    }

    private static void processFile(File file) {
        System.out.println("Processing file: " + file.getName());
        File resultFile = new File(file.getParent(), file.getName().replaceFirst("[.][^.]+$", "") + ".res");

        try (BufferedReader reader = new BufferedReader(new FileReader(file));
             PrintWriter writer = new PrintWriter(new FileWriter(resultFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String result = processLine(line);
                writer.println(result);
            }
        } catch (IOException e) {
            System.err.println("Error processing file: " + file.getName());
            e.printStackTrace();
        }
    }

    private static String processLine(String line) {
        String[] parts = line.split(" ");
        if (parts.length == 3) {
            try {
                int num1 = Integer.parseInt(parts[0]);
                int num2 = Integer.parseInt(parts[1]);
                String op = parts[2];

                switch (op) {
                    case "+":
                        return String.valueOf(num1 + num2);
                    case "-":
                        return String.valueOf(num1 - num2);
                    case "*":
                        return String.valueOf(num1 * num2);
                    default:
                        return "Error: Unknown operation";
                }
            } catch (NumberFormatException e) {
                return "Error: Invalid number format";
            }
        } else {
            return "Error: Incorrect format";
        }
    }
}