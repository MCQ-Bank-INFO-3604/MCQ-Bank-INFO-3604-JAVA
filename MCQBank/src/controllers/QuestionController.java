package controllers;

import java.time.LocalDateTime;
import models.Question;
import views.QuestionView;


public class QuestionController {

    private Question model;
    private QuestionView view;

    public QuestionController(Question model, QuestionView view){

        this.model = model;
        this.view = view;

    }


    //models


    public String getQuestion() {
        return model.getQuest();
    }

    public void setQuest(String quest) {
        model.setQuest(quest);
    }

    public String getCanswer() {
        return model.getCanswer();
    }

    public void setCanswer(String Canswer) {
        model.setCanswer(Canswer);
    }

    public String getWanswer1() {
        return model.getWanswer1();
    }

    public void setWanswer1(String Wanswer1) {
        model.setWanswer1(Wanswer1);
    }

    public String getWanswer2() {
        return model.getWanswer2();
    }

    public void setWanswer2(String Wanswer2) {
        model.setWanswer2(Wanswer2);
    }

    public String getWanswer3() {
        return model.getWanswer3();
    }

    public void setWanswer3(String Wanswer3) {
        model.setWanswer3(Wanswer3);
    }

    public String getCourse() {
        return model.getCourse();
    }

    public void setCourse(String Course) {
        model.setCourse(Course);
    }

    public String getTopic() {
        return model.getTopic();
    }

    public void setTopic(String Topic) {
        model.setTopic(Topic);
    }

    public String getSubTopic() {
        return model.getSubTopic();
    }

    public void setSubTopic(String subTopic) {
        model.setSubTopic(subTopic);
    }

    public String getDifficulty() {
        return model.getDifficulty();
    }

    public void setDifficulty(String difficulty) {
        model.setDifficulty(difficulty);
    }

    // public Image getImg() {
    //     return model.getImg();
    // }

    // public void setImg(Image img) {
    //     model.setImg(img);
    // }

    public LocalDateTime getDateCreated() {
        return model.getDateCreated();
    }

    public void setDateCreated(LocalDateTime dateCreated) {
        model.setDateCreated(dateCreated);
    }

    public LocalDateTime getLastUsed() {
        return model.getLastUsed();
    }

    public void setLastUsed(LocalDateTime lastUsed) {
        model.setLastUsed(lastUsed);
    }

    public int getTimesUsed(){
        return model.getTimesUsed();
    }

    public void setTimesUsed(int timesUsed){
        model.setTimesUsed(timesUsed);
    }

    public float getPerformanceMetric(){
        return model.getPerformanceMetric();
    }

    public void getPerformanceMetric(float perfomanceMetric){
        model.setPerformanceMetric(perfomanceMetric);
    }

    //views
    // public void updateView(){
    //     view.printQuestion(getQuestion(), getCanswer(), getWanswer1(), getWanswer2(), getWanswer3(), getCourse(), getTopic(), getSubTopic(), getDifficulty() , getDateCreated() , getLastUsed(), getTimesUsed(), getPerformanceMetric());

    // }

    public static void create_question(String quest,String Canswer, String Wanswer1, String Wanswer2, String Wanswer3, String Course, String Topic, String subTopic, String difficulty){
        Question newQuestion = new Question(quest,Canswer,Wanswer1,Wanswer2,Wanswer3,Course,Topic,subTopic, difficulty);
        
        Question.getAllQuestions().add(newQuestion);
    
    }

    public static Question retrieveQuestion(int questionID){
        for(Question q : Question.getAllQuestions()){
            if (q.getQuestionID()==questionID){
                return q;
            }
        }
        return null;
    }

    public static void delete_question(int questionID)
    {
        Question q = retrieveQuestion(questionID);
        if (q==null)
        {
            System.out.println("No question was found");
        }
        else{
            Question.getAllQuestions().remove(q);
            // System.out.println("The question : " + q.getQuest() + " has been deleted.");
        }
        

    }


    public static void edit_question(int questionID, Question q2){
        Question q = retrieveQuestion(questionID);
        
        if (q == null)
        {
            System.out.println("No question was found, please try again");
        }
        else
        {
            q.setQuest(q2.getQuest());
            q.setCanswer(q2.getCanswer());
            q.setWanswer1(q2.getWanswer1());
            q.setWanswer2(q2.getWanswer2());
            q.setWanswer3(q2.getWanswer3());
            q.setCourse(q2.getCourse());
            q.setTopic(q2.getTopic());
            q.setSubTopic(q2.getSubTopic());
            q.setDifficulty(q2.getDifficulty());
            // q.setDateCreated(q2.getDateCreated());
            // q.setLastUsed(q2.getLastUsed());

        }
    }
}
