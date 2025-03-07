// import java.io.BufferedWriter;
// import java.io.FileWriter;
//import java.io.File;  // Import the File class
//import java.io.IOException;  // Import the IOException class to handle errors
//import java.util.ArrayList;
//import java.util.Scanner;
// import java.time.LocalDateTime;
// import java.time.format.DateTimeFormatter;

import controllers.*;
import models.*;
import views.*;

public class App {
    // File questionBank = new File("questionBank.csv");
    // File examBank = new File("examBank.csv");

    public static void main(String[] args) throws Exception {
        //Question model = 
        //retrieveQuestionsFromDatabase();
        // QuestionView view = new QuestionView();
        // QuestionController controller = new QuestionController(model, view);
        
        QuestionController.create_question("What is the capital of France?","Paris", "Berlin","Madrid","Rome", "Geography", "Geography", "Countries", "Easy");
        QuestionController.create_question("What is the chemical symbol for gold?","Au","Ag", "Pb", "Fe", "Chemistry", "Periodic Table", "Elements", "Medium");
        QuestionController.create_question("Which planet is known as the Red Planet?", "Mars", "Venus", "Jupiter", "Saturn", "Astronomy", "Solar System", "Planets", "Easy");
        System.out.println("Created 3 Questions\n\n");

        System.out.println("Prints All Questions: ");
        QuestionView.printAllQuestions(Question.getAllQuestions());

        System.out.println("\n\nPrint just the second ID question: ");
        QuestionView.printQuestion(2);

        QuestionController.edit_question(2, new Question("In what year did World War II end?", "1945", "1939",    "1918", "1950", "History", "World War II", "Major Events", "Hard"));
        System.out.println("\n\nQuestion 2 is Edited");
        
        System.out.println("\n\nPrint just the second ID question again: ");
        QuestionView.printQuestion(2);

        QuestionController.delete_question(2);
        System.out.println("\n\nQuestion 2 is Deleted");

        System.out.println("\n\nPrint All Questions Again: ");
        QuestionView.printAllQuestions(Question.getAllQuestions());

        ExamController.create_exam(0, "COMP2600", "Topic", "subTopic", "ifficulty");
        System.out.println("\n\nExam 1 is created");

        System.out.println("\n\nPrint Exam");
        ExamView.printExam(1);

        System.out.println("\n\nQuestion 1 is added to Exam 1 and then prints the exam again");
        ExamController.addQuestionToExam(1,1);
        ExamView.printExam(1);

        System.out.println("\n\nQuestion 3 is added to Exam 1 and then prints the exam again");
        ExamController.addQuestionToExam(3,1);
        ExamView.printExam(1);

        System.out.println("\n\nQuestion 1 is removed from Exam 1 and then prints the exam again");
        ExamController.removeQuestionFromExam(1,1);
        ExamView.printExam(1);

        System.out.println("\n\nPrints all questions again to see last used dates change ");

        QuestionView.printAllQuestions(Question.getAllQuestions());
        //Question question5 = new Question("In what year did World War II end?", "1945", "1939",    "1918", "1950", "History", "World War II", "Major Events", "Hard");

        // for(Question q : Question.getAllQuestions()){
        //     System.out.println(q.getQuestionID());
        // }
        //         //System.out.println(models.Question.getAllQuestions());
                //controller.updateView();
    }
        

        
    // private void readQuestionBank(){
    //     Scanner read = new Scanner(questionBank);
    //     read.nextLine();
    //     read.useDelimiter(",|\n");
    //     DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")

    //     while (read.hasNext()){
    //         int questionID = Integer.valueOf(read.next());
    //         String quest = read.next();
    //         String Canswer = read.next();
    //         String Wanswer1 = read.next();
    //         String Wanswer2 = read.next();
    //         String Wanswer3 = read.next();
    //         String Course = read.next();
    //         String Topic = read.next();
    //         String subTopic = read.next();
    //         String difficulty = read.next();
    //         LocalDateTime lastUsed = LocalDateTime.parse(read.next(),formatter);
    //         LocalDateTime lastEdited = LocalDateTime.parse(read.next(),formatter);
    //         int timesUsed = Integer.valueOf(read.next());
    //         float performanceMetric = Float.valueOf(read.next());

    //         QuestionController.create_question(questionID, quest, Canswer, Wanswer1, Wanswer2, Wanswer3, Course, Topic, subTopic, difficulty, lastUsed, lastEdited, timesUsed, performanceMetric);

    //     }

    // }

    // private static void writeQuestionBank(ArrayList<Question> questions, String filename){
        
    // }

    // private void readExamBank(){

    // }

    // private void writeExamBank(){
        
    // }

}
