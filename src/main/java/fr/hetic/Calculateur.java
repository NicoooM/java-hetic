package fr.hetic;

import java.io.*;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Calculateur {
    public static void main(String[] args) {
        try (ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("beans.xml")) {
            File folder = new File("operations");

            if (!folder.exists()) {
                System.out.println("Directory does not exist");
                return;
            }

            FileProcessor processor = context.getBean("fileProcessor", FileProcessor.class);

            processor.processDirectory(folder);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
