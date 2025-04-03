package models;

import java.sql.*;


public class Exam {
    private static final String DB_URL = "jdbc:sqlite:mcq_bank.db?journal_mode=WAL&busy_timeout=3000";
    
    public Exam(){
        deleteExamTable();
        createExamTable();
    }

    private void deleteExamTable() {
        String sql = "DROP TABLE IF EXISTS exams;";
        
        try (Connection conn = DriverManager.getConnection(DB_URL);
             Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }    

    private void createExamTable() {
        String sql = "CREATE TABLE IF NOT EXISTS exams ("
                   + "examID INTEGER PRIMARY KEY AUTOINCREMENT,"
                   + "numQuestions INTEGER,"
                   + "course TEXT,"
                   + "topic TEXT,"
                   + "subTopic TEXT,"
                   + "difficulty TEXT,"
                   + "dateCreated DATETIME,"
                   + "lastUsed DATETIME,"
                   + "lastEdited DATETIME);";
        
        try (Connection conn = DriverManager.getConnection(DB_URL);
             Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }        

}
