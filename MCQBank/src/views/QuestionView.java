package views;
import controllers.QuestionController;
import java.util.*;
import models.Question;

public class QuestionView {

    public void printMinimalQuestion(String quest,String Canswer, String Wanswer1, String Wanswer2, String Wanswer3){
        System.out.println("Question: " + quest);
        System.out.println("Correct Answer: " + Canswer);
        System.out.println("Wrong Answer: " + Wanswer1);
        System.out.println("Wrong Answer: " + Wanswer2);
        System.out.println("Wrong Answer: " + Wanswer3);


    }

    public void printShuffledQuestion(String question, String Answer1, String Answer2, String Answer3, String Answer4){

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




    public static void printQuestion(int QuestionID){
        Question q = QuestionController.retrieveQuestion(QuestionID);
        if (q==null)
        {
            System.out.println("No question found");
        }
        else
        {
            System.out.println("Question ID: " + Integer.toString(q.getQuestionID()));
            System.out.println("Question: " + q.getQuest());
            System.out.println("Correct Answer: " + q.getCanswer());
            System.out.println("Wrong Answer: " + q.getWanswer1());
            System.out.println("Wrong Answer: " + q.getWanswer2());
            System.out.println("Wrong Answer: " + q.getWanswer3());
            System.out.println("Course Code: " + q.getCourse());
            System.out.println("Topic: " + q.getTopic());
            System.out.println("Sub-Topic: " + q.getSubTopic());
            System.out.println("Difficulty: " + q.getDifficulty());
            System.out.println("Date Created: " + q.getDateCreated());
            System.out.println("Last Used: " + q.getLastUsed());
            System.out.println("Last Edited: " + q.getLastEdited());
            System.out.println("Times Used: " + q.getTimesUsed());
            System.out.println("Performance Metric: " + q.getPerformanceMetric());
        }
        //System.out.println("Image: " + img);


    }




    public static void printAllQuestions(ArrayList<Question> allQuestions){
        for(Question q : Question.getAllQuestions()){
            System.out.println("Question ID: " + Integer.toString(q.getQuestionID()));
            System.out.println("Question: " + q.getQuest());
            System.out.println("Correct Answer: " + q.getCanswer());
            System.out.println("Wrong Answer: " + q.getWanswer1());
            System.out.println("Wrong Answer: " + q.getWanswer2());
            System.out.println("Wrong Answer: " + q.getWanswer3());
            System.out.println("Course Code: " + q.getCourse());
            System.out.println("Topic: " + q.getTopic());
            System.out.println("Sub-Topic: " + q.getSubTopic());
            System.out.println("Difficulty: " + q.getDifficulty());
            System.out.println("Date Created: " + q.getDateCreated());
            System.out.println("Last Used: " + q.getLastUsed());
            System.out.println("Last Edited: " + q.getLastEdited());
            System.out.println("Times Used: " + q.getTimesUsed());
            System.out.println("Performance Metric: " + q.getPerformanceMetric());
        }
    }


}
