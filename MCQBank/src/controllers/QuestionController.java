package controllers;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;


public class QuestionController {
    private static final String DB_URL = "jdbc:sqlite:mcq_bank.db?journal_mode=WAL&busy_timeout=3000";

    public void insertQuestion(String question, String correctAnswer, String wrong1, String wrong2, String wrong3, String course, String topic, String subTopic, String difficulty) {
        String sql = "INSERT INTO questions (question, correctAnswer, wrongAnswer1, wrongAnswer2, wrongAnswer3, course, topic, subTopic, difficulty, dateCreated) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
        
        // java.util.Date today=new Date();
        // java.sql.Date date=new java.sql.Date(today.getTime()); //your SQL date object
        String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, question);
            pstmt.setString(2, correctAnswer);
            pstmt.setString(3, wrong1);
            pstmt.setString(4, wrong2);
            pstmt.setString(5, wrong3);
            pstmt.setString(6, course);
            pstmt.setString(7, topic);
            pstmt.setString(8, subTopic);
            pstmt.setString(9, difficulty);

            pstmt.setString(10, date);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void insertQuestionsFromCSV(String filename){

        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(","); // Assuming the CSV is comma-separated
                String question = values[0];
                String correctAnswer = values[1];
                String wrong1 = values[2];
                String wrong2 = values[3];
                String wrong3 = values[4];
                String course = values[5];
                String topic = values[6];
                String subTopic = values[7];
                String difficulty = values[8];

                // Pass data to the next step
                insertQuestion(question, correctAnswer, wrong1, wrong2, wrong3, course, topic, subTopic, difficulty);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }    


    public ResultSet getQuestion(int questionID){
        
        String sql = "SELECT * FROM questions WHERE questionID = '" + questionID + "';";
        try {
            Connection conn = DriverManager.getConnection(DB_URL);
            Statement stmt = conn.createStatement();
            return stmt.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }        
    }

    public void deleteQuestion(int questionID){
        
        String sql = "DELETE FROM questions WHERE questionID = '" + questionID + "';";
        try {
            Connection conn = DriverManager.getConnection(DB_URL);
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
            return;
        }        
    }


    public void editQuestion(int questionID, String question, String correctAnswer, String wrong1, String wrong2, String wrong3, String course, String topic, String subTopic, String difficulty) {
        java.util.Date today=new Date();
        java.sql.Date date=new java.sql.Date(today.getTime()); //your SQL date object

        String sql = "UPDATE questions " +
                    "SET question = '" + question + "' ,"+
                    "correctAnswer = '" + correctAnswer + "' ,"+
                    "wrongAnswer1 = '" + wrong1 + "' ,"+
                    "wrongAnswer2 = '" + wrong2 + "' ,"+
                    "wrongAnswer3= '" + wrong3 + "' ,"+
                    "course = '" + course + "' ,"+
                    "topic = '" + topic + "' ,"+
                    "subTopic = '" + subTopic + "' ,"+
                    "difficulty = '" + difficulty + "' ,"+
                    "lastEdited = '" + date + "' " +
                    "WHERE questionID = '" + questionID + "';";


        try {
            Connection conn = DriverManager.getConnection(DB_URL);
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
            return;
        }        
    }

    public ResultSet getTopics(){
        
        String sql = "SELECT DISTINCT topic FROM questions;";
        try {
            Connection conn = DriverManager.getConnection(DB_URL);
            Statement stmt = conn.createStatement();
            return stmt.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }        
    }     

    public ResultSet getQuestionsWithFilter(String filter, String search){
        
        String sql = "SELECT * FROM questions WHERE " + filter +" = '" + search + "';";
        try {
            Connection conn = DriverManager.getConnection(DB_URL);
            Statement stmt = conn.createStatement();
            return stmt.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }        
    }    

    public ResultSet getAllQuestions() {
        String sql = "SELECT * FROM questions";
        
        try {
            Connection conn = DriverManager.getConnection(DB_URL);
            Statement stmt = conn.createStatement();
            return stmt.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    
    

}
