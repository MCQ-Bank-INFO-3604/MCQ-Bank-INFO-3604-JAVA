import java.sql.*;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;

public class DatabaseHelper {
    private static final String DB_URL = "jdbc:sqlite:mcq_bank.db?journal_mode=WAL&busy_timeout=3000";

    public DatabaseHelper() {
        deleteQuestionTable();
        deleteExamTable();
        deleteExamQuestionsTable();
        createExamTable();
        createQuestionTable();
        createExamQuestionsTable();
        
        

    }

    //TABLES

    private void deleteQuestionTable() {
        String sql = "DROP TABLE IF EXISTS questions;";
        
        try (Connection conn = DriverManager.getConnection(DB_URL);
             Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
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

    private void deleteExamQuestionsTable() {
        String sql = "DROP TABLE IF EXISTS ExamQuestions;";
        
        try (Connection conn = DriverManager.getConnection(DB_URL);
             Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }        

    private void createQuestionTable() {
        String sql = "CREATE TABLE IF NOT EXISTS questions ("
                   + "questionID INTEGER PRIMARY KEY AUTOINCREMENT,"
                   + "question TEXT NOT NULL,"
                   + "correctAnswer TEXT NOT NULL,"
                   + "wrongAnswer1 TEXT NOT NULL,"
                   + "wrongAnswer2 TEXT NOT NULL,"
                   + "wrongAnswer3 TEXT NOT NULL,"
                   + "course TEXT,"
                   + "topic TEXT,"
                   + "subTopic TEXT,"
                   + "difficulty TEXT,"
                   + "dateCreated DATETIME,"
                   + "lastUsed DATETIME,"
                   + "lastEdited DATETIME,"
                   + "timesUsed INTEGER,"
                   + "performanceMetric FLOAT);";
        
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

    private void createExamQuestionsTable() {
        String sql = "CREATE TABLE IF NOT EXISTS ExamQuestions ("
                   + "examID INTEGER,"
                   + "questionID INTEGER,"        
                   + "PRIMARY KEY (examID, questionID),"
                   + "FOREIGN KEY (examID) REFERENCES exams(examID),"
                   + "FOREIGN KEY (questionID) REFERENCES questions(questionID));";
        
        try (Connection conn = DriverManager.getConnection(DB_URL);
             Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }        

    //QUESTIONS
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

    public void printTopics(ResultSet rs) throws SQLException{
        while (rs != null && rs.next())
            System.out.println("Topic: " + rs.getString("topic"));

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


    public void printQuestions(ResultSet rs) throws SQLException{

        while (rs != null && rs.next())
        {
            System.out.println(rs.getString("questionID"));
            System.out.println(rs.getString("question"));
            System.out.println(rs.getString("correctAnswer"));
            System.out.println(rs.getString("wrongAnswer1"));
            System.out.println(rs.getString("wrongAnswer2"));
            System.out.println(rs.getString("wrongAnswer3"));
            System.out.println(rs.getString("course"));
            System.out.println(rs.getString("topic"));
            System.out.println(rs.getString("subTopic"));
    
            System.out.println(rs.getString("difficulty"));
            System.out.println(rs.getDate("dateCreated"));
    
        }

    }


    //EXAMS
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


    public void addQuestionToExam(int examID, int questionID) throws SQLException {
        //java.util.Date today=new Date();
        //java.sql.Date date=new java.sql.Date(today.getTime()); //your SQL date object
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


        // ResultSet rs = getQuestion(questionID);
        


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
        //java.util.Date today=new Date();
        //java.sql.Date date=new java.sql.Date(today.getTime()); //your SQL date object
        String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        ResultSet rs2 = getExam(examID);


        String sql = "UPDATE exams " +
                        "SET lastEdited = '" + date + "' ," +
                        "numQuestions = '"+ (rs2.getInt("numQuestions") - 1) +  "' " +
                        "WHERE examID = '" + examID + "';";


        // ResultSet rs = getQuestion(questionID);
        // ResultSet rs2 = getExam(examID);

        try {
            Connection conn = DriverManager.getConnection(DB_URL);
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
            return;
        }        



        sql = "DELETE FROM ExamQuestions WHERE questionID = '" + questionID + "' AND examID = '" + examID + "' ;";
                    // "wrongAnswer1 = '" + rs.getString("correctAnswer") + "' ,"+
                    // "wrongAnswer2 = '" + rs.getString("correctAnswer") + "' ,"+
                    // "wrongAnswer3= '" + rs.getString("correctAnswer") + "' ,"+
                    // "course = '" + rs.getString("correctAnswer") + "' ,"+
                    // "topic = '" + rs.getString("correctAnswer") + "' ,"+
                    // "subTopic = '" + rs.getString("correctAnswer") + "' ,"+
                    // "difficulty = '" + rs.getString("correctAnswer") + "' ,"+
                    // "lastEdited = '" + rs.getString("correctAnswer") + "' " +
                    // "WHERE questionID = '" + questionID + "';";
        try {
            Connection conn = DriverManager.getConnection(DB_URL);
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
            return;
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

    public void printExamQuestions(ResultSet rs) throws SQLException{

        ResultSet exam = getExam(rs.getInt("examID"));
        System.out.println("Exam ID :"  + exam.getInt("examID"));
        System.out.println("Number of Questions :"  + exam.getInt("numQuestions"));
        System.out.println("Course :"  + exam.getString("course"));
        System.out.println("Topic :"  + exam.getString("topic"));
        System.out.println("Sub-Topic :"  + exam.getString("subTopic"));
        System.out.println("Difficulty :"  + exam.getString("difficulty"));
        System.out.println("Date Created :"  + exam.getString("dateCreated"));
        System.out.println("Last Used :"  + exam.getString("lastUsed"));
        System.out.println("Last Edited :"  + exam.getString("lastEdited"));

        //String sql = "SELECT * FROM ExamQuestions WHERE examID = '" + examID + "' ;";


        while (rs != null && rs.next())
        {
            ResultSet question = getQuestion(rs.getInt("questionID"));

    

            System.out.println("Question ID: " + rs.getInt("questionID"));

            System.out.println("Question: " + question.getString("question"));
            System.out.println("Correct Answer: " + question.getString("correctAnswer"));
            System.out.println("Wrong Answer1: "+question.getString("wrongAnswer1"));
            System.out.println("Wrong Answer2: "+question.getString("wrongAnswer2"));
            System.out.println("Wrong Answer3: "+question.getString("wrongAnswer3"));
            // System.out.println("Course: " +  question.getString("course"));
            // System.out.println("Topic: " + question.getString("topic"));
            // System.out.println("subTopic: " +question.getString("subTopic"));
    
            // System.out.println("Difficulty: " + question.getString("difficulty"));
            // System.out.println("DateCreated: " + question.getString("dateCreated"));
            // System.out.println("Last Used: "+ question.getString("lastUsed"));
            // System.out.println("Last Edited: "+ question.getString("lastEdited"));
            // System.out.println("Times Used: "+ question.getInt("timesUsed"));
            // System.out.println("Performance Metric: "+ question.getFloat("performanceMetric"));
    
        }

    }

}

