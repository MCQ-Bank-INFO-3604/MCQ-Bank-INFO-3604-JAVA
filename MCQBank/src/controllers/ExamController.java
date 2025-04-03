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
import java.util.ArrayList;
import java.util.Date;

import models.Exam;
import models.Question;
import views.ExamView;


public class ExamController {
    private static final String DB_URL = "jdbc:sqlite:mcq_bank.db?journal_mode=WAL&busy_timeout=3000";

    public void insertExam(int numQuestions, String course, String topic, String subTopic, String difficulty) {
        String sql = "INSERT INTO exams (numQuestions, course, topic, subTopic, difficulty, dateCreated) VALUES ( ?, ?, ?, ?, ?, ? );";
        
        //java.util.Date today=new Date();
        //java.sql.Date date=new java.sql.Date(today.getTime()); //your SQL date object
        String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, numQuestions);
            pstmt.setString(2, course);
            pstmt.setString(3, topic);
            pstmt.setString(4, subTopic);
            pstmt.setString(5, difficulty);
            pstmt.setString(6, date);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ResultSet getExam(int examID){
        
        String sql = "SELECT * FROM exams WHERE examID = '" + examID + "';";
        try {
            Connection conn = DriverManager.getConnection(DB_URL);
            Statement stmt = conn.createStatement();
            return stmt.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
          }        
    }

    public ResultSet getQuestionsFromExam(int examID){

        String sql = "SELECT * FROM ExamQuestions WHERE examID = '" + examID + "' ;";
        try {
            Connection conn = DriverManager.getConnection(DB_URL);
            Statement stmt = conn.createStatement();
            return stmt.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

    }
    
    public void addQuestionToExam(int examID, int questionID) throws SQLException {
        ResultSet rs2 = getExam(examID);
        
        String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());

        String sql = "UPDATE exams " +
                    "SET lastEdited  = '" + date + "',numQuestions = '" + (rs2.getInt("numQuestions") + 1) + "'  WHERE examID = '" + examID + "';";

        try{
            Connection conn1 = DriverManager.getConnection(DB_URL);
            Statement stmt = conn1.createStatement();
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
            return;
        }        
                   
        sql = "UPDATE questions " +
            "SET lastUsed = '" + date + "' " +
            "WHERE questionID = '" + questionID + "';";

        try {
            Connection conn2 = DriverManager.getConnection(DB_URL);
            Statement stmt = conn2.createStatement();
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
            return;
        }        

        sql = "INSERT INTO ExamQuestions (examID, questionID) VALUES (? ,?);"; 

        try (Connection conn3 = DriverManager.getConnection(DB_URL);
        PreparedStatement pstmt = conn3.prepareStatement(sql)) {
       pstmt.setInt(1, examID);
       pstmt.setInt(2, questionID);
       pstmt.executeUpdate();

   } catch (SQLException e) {
       e.printStackTrace();
   }
    }

    
    public void removeQuestionFromExam(int examID, int questionID) throws SQLException {
        String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        ResultSet rs2 = getExam(examID);


        String sql = "UPDATE exams " +
                        "SET lastEdited = '" + date + "' ," +
                        "numQuestions = '"+ (rs2.getInt("numQuestions") - 1) +  "' " +
                        "WHERE examID = '" + examID + "';";

        try {
            Connection conn = DriverManager.getConnection(DB_URL);
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
            return;
        }        



        sql = "DELETE FROM ExamQuestions WHERE questionID = '" + questionID + "' AND examID = '" + examID + "' ;";

        try {
            Connection conn = DriverManager.getConnection(DB_URL);
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
            return;
        }        
    }    


    // public void generateExam(int numQuestions, String Course, String Topic, String subTopic, String difficulty){
    //     Exam egg = new Exam(numQuestions, Course, Topic, subTopic, difficulty);
    //     ArrayList<Question> questions = retrieveQuestions(numQuestions, Course, Topic, subTopic, difficulty);
    //     egg.setQuestions(questions);
    // }

}
