package fr.hetic;

import java.io.*;


public class FileProcessor {

    public void processDirectory(File folder) {
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

    private void processFile(File file) {
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
