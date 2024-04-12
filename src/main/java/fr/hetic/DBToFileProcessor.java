package fr.hetic;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.nio.file.*;

public class DBToFileProcessor {
    private static final String FETCH_FILES_SQL = "SELECT * FROM fichier";

    public static void processFilesFromDB() {
        Connection conn = DatabaseConnector.connect();
        if (conn == null) {
            System.out.println("Unable to connect to the database.");
            return;
        }

        try {
            // get all files
            PreparedStatement fetchFilesStmt = conn.prepareStatement(FETCH_FILES_SQL);
            ResultSet filesResult = fetchFilesStmt.executeQuery();

            while (filesResult.next()) {
                int fileId = filesResult.getInt("id");
                String fileName = filesResult.getString("nom");
                System.out.println("Processing file: " + fileName);

                writeResult(conn, fileId, fileName);

            }

        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
        } finally {
            DatabaseConnector.closeConnection(conn);
        }
    }

    private static void writeResult(Connection conn, int fileId, String fileName) throws SQLException {
        List<String> operations = new ArrayList<>();
        String fetchLinesSQL = "SELECT * FROM ligne WHERE fichier_id = ? ORDER BY position";
        PreparedStatement fetchLinesStmt = conn.prepareStatement(fetchLinesSQL);
        fetchLinesStmt.setInt(1, fileId);

        ResultSet linesResult = fetchLinesStmt.executeQuery();
        while (linesResult.next()) {
            try {
                int param1 = linesResult.getInt("param1");
                int param2 = linesResult.getInt("param2");
                String operator = linesResult.getString("operateur");

                Operation operation = OperationFactory.getOperation(operator);
                String result = String.valueOf(operation.execute(param1, param2));
                operations.add(result);
            } catch (Exception e) {
                System.out.println("Error processing line: " + e.getMessage());
            }
        }

        Path path = Path.of("db-output/" + fileName + ".res");
        FileProcessor.writeFile(path, operations);
    }


    public static void main(String[] args) {
        processFilesFromDB();
    }
}
