import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

public class CSVToSQLite {
    public static void main(String[] args) {
        // Path to your CSV file
        String csvFilePath = "data.csv";
        // SQLite database file
        String dbURL = "jdbc:sqlite:mydatabase.db";

        // SQL statement to create a table (adjust column names and types as needed)
        String createTableSQL = "CREATE TABLE IF NOT EXISTS mytable ("
                              + "column1 TEXT, "
                              + "column2 TEXT, "
                              + "column3 TEXT)";

        // SQL for inserting data
        String insertSQL = "INSERT INTO mytable (column1, column2, column3) VALUES (?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(dbURL)) {
            // Create table if it doesn't exist
            try (Statement stmt = conn.createStatement()) {
                stmt.execute(createTableSQL);
            }

            // Open CSV file using OpenCSV
            try (CSVReader reader = new CSVReader(new FileReader(csvFilePath))) {
                List<String[]> rows = reader.readAll();
                
                // Prepare insert statement
                try (PreparedStatement pstmt = conn.prepareStatement(insertSQL)) {
                    // Optional: skip the header row if your CSV has one
                    boolean skipHeader = true;
                    for (String[] row : rows) {
                        if (skipHeader) {
                            skipHeader = false;
                            continue;
                        }
                        // Assuming your CSV has at least three columns; adjust as needed
                        pstmt.setString(1, row[0]);
                        pstmt.setString(2, row[1]);
                        pstmt.setString(3, row[2]);
                        pstmt.addBatch();
                    }
                    // Execute batch insert for efficiency
                    pstmt.executeBatch();
                }
            } catch (CsvException e) {
                System.err.println("Error reading CSV file: " + e.getMessage());
            }
        } catch (SQLException | IOException e) {
            System.err.println("Database error: " + e.getMessage());
        }
    }
}
