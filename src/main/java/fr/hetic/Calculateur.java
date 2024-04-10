package fr.hetic;

import java.io.*;

public class Calculateur {
    public static void main(String[] args) {
        File folder = new File("./fr/hetic/operations");

        if (!folder.exists()) {
            System.out.println("Directory does not exist");
            return;
        }

        FileProcessor processor = new FileProcessor();
        processor.processDirectory(folder);
    }
}