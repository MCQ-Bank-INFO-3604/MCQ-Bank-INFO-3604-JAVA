package views;
import controllers.ExamController;
import java.util.*;
import models.*;


public class ExamView{

    public static void printShuffledQuestion(String question, String Answer1, String Answer2, String Answer3, String Answer4){

        List<String> li = new ArrayList<>();
        li.add(Answer1);
        li.add(Answer2);
        li.add(Answer3);
        li.add(Answer4);

        Collections.shuffle(li);

        System.out.println("Question: " + question);
        System.out.println("A: " + li.get(0));
        System.out.println("B: " + li.get(1));
        System.out.println("C: " + li.get(2));
        System.out.println("D: " + li.get(3));


    }



    public static void printExam(int ExamID){
        Exam egg = ExamController.retrieveExam(ExamID);
        int number = 0;
        if (egg==null)
        {
            System.out.println("No Exam found");
        }
        else
        {
            System.out.println("Exam ID: " + Integer.toString(egg.getExamID()));
            System.out.println("Number of Questions: " + egg.getNumQuestions());
            System.out.println("Course Code: " + egg.getCourse());
            System.out.println("Topic: " + egg.getTopic());
            System.out.println("Sub-Topic: " + egg.getSubTopic());
            System.out.println("Difficulty: " + egg.getDifficulty());
            System.out.println("Date Created: " + egg.getDateCreated());
            System.out.println("Last Used: " + egg.getLastUsed());
            System.out.println("Last Edited: " + egg.getLastEdited());
            System.out.println("Questions: ");
            for (Question q : egg.getQuestions())
            {
                number++;
                System.out.print(Integer.toString(number) + ". ");
                printShuffledQuestion(q.getQuest(),q.getCanswer(),q.getWanswer1(),q.getWanswer2(),q.getWanswer3());
            }
            
            if (number==0)
            {
                System.out.println("No Questions Added to this Exam as yet");
            }
        }
        //System.out.println("Image: " + img);


    }


}