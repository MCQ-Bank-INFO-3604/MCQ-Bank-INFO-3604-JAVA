import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import controllers.*;
import models.*;
import views.*;

public class App {


    public static void main(String[] args) throws Exception {

        //Create Models
        Question question = new Question();
        Exam exam = new Exam();
        ExamQuestions examQuestions = new ExamQuestions();
        
        //Create Contollers
        QuestionController questionController = new QuestionController();
        ExamController examController = new ExamController();
        
        //Create Views
        QuestionView questionView = new QuestionView();
        ExamView examView = new ExamView();


        //READING FROM A CSV
        // String filename = "File.csv";
        // System.out.println("Inserts Questions from, CSV: ");
        // questionController.insertQuestionsFromCSV(filename);

        // ResultSet rs = questionController.getAllQuestions();
        // questionView.printQuestions(rs);


        //SHOWCASE DATABASE
        System.out.println("Inserts 3 Questions: ");
        questionController.insertQuestion("What is the capital of France?","Paris", "Berlin","Madrid","Rome", "Geography", "Geography", "Countries", "Easy");

        questionController.insertQuestion("What is the chemical symbol for gold?","Au","Ag", "Pb", "Fe", "Chemistry", "Periodic Table", "Elements", "Medium");
        

        questionController.insertQuestion("Which planet is known as the Red Planet?", "Mars", "Venus", "Jupiter", "Saturn", "Astronomy", "Solar System", "Planets", "Easy");

        System.out.println("\n\nPrints All Questions: \n");
        ResultSet rs = questionController.getAllQuestions();
        questionView.printQuestions(rs);

        System.out.println("\n\nEdits question 2 and Prints All Questions: \n");
        questionController.editQuestion(2,"In what year did World War II end?", "1945", "1939",    "1918", "1950", "History", "World War II", "Major Events", "Hard");

        rs = questionController.getAllQuestions();
        questionView.printQuestions(rs);

        System.out.println("\n\nDeletes question 3 and Prints All Questions: \n");
        questionController.deleteQuestion(3);

        rs = questionController.getAllQuestions();
        questionView.printQuestions(rs);
        
        System.out.println("\n\nPrints Questions 1: \n");
        rs = questionController.getQuestion(1);
        questionView.printQuestions(rs);

        
        System.out.println("\n\nPrints All Unique Topics in Database: \n");
        rs = questionController.getTopics();
        questionView.printTopics(rs);

        System.out.println("\n\nInsert exam, adds question 1 and prints questions\n");

        examController.insertExam(0, "COMP2600", "Topic", "subTopic", "ifficulty");

        examController.addQuestionToExam(1,1);
        rs = examController.getQuestionsFromExam(1);
        examView.printExamQuestions(rs);

        System.out.println("\n\n Adds question 2 and prints exam questions again \n");
        examController.addQuestionToExam(1,2);
        rs = examController.getQuestionsFromExam(1);
        examView.printExamQuestions(rs);

        System.out.println("\n\nRemove Question 1 and prints exam questions again \n");
        examController.removeQuestionFromExam(1, 1);
        rs = examController.getQuestionsFromExam(1);
        examView.printExamQuestions(rs); 

    }
}
