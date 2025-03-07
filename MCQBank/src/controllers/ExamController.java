package controllers;

import java.time.LocalDateTime;
import java.util.ArrayList;
import models.*;
import views.*;

public class ExamController {
    private Exam model;
    private ExamView view;

    public ExamController(Exam model, ExamView view){

        this.model = model;
        this.view = view;

    }


    //models

    public ArrayList<Question> getQuestions() {
        return model.getQuestions();
    }

    public void setQuestions(ArrayList<Question> questions) {
        model.setQuestions(questions);
    }

    public int getExamID() {
        return model.getExamID();
    }

    public int getNumQuestions() {
        return model.getNumQuestions();
    }

    public void setNumQuestions(int numQuestions) {
        model.setNumQuestions(numQuestions);
    }
    
    public String getCourse() {
        return model.getCourse();
    }

    public void setCourse(String course) {
        model.setCourse(course);
    }

    public String getTopic() {
        return model.getTopic();
    }

    public void setTopic(String topic) {
        model.setTopic(topic);
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

    public LocalDateTime getDateCreated() {
        return model.getDateCreated();
    }

    public void setDateCreated(LocalDateTime dateCreated) {
        model.setDateCreated(dateCreated);
    }

    // Getter and Setter for lastUsed
    public LocalDateTime getLastUsed() {
        return model.getLastUsed();
    }

    public void setLastUsed(LocalDateTime lastUsed) {
        model.setLastUsed(lastUsed);
    }
    
    public LocalDateTime getLastEdited() {
        return model.getLastEdited();
    }

    public void setLastEdited(LocalDateTime lastEdited) {
        model.setLastEdited(lastEdited);
    }    

    public static Question retrieveQuestion(int questionID){
        for(Question q : Question.getAllQuestions()){
            if (q.getQuestionID()==questionID){
                return q;
            }
        }
        return null;
    }    

    public static ArrayList<Question> retrieveQuestions(int numQuestions, String Course, String Topic, String subTopic, String difficulty){
        ArrayList<Question> questions = new ArrayList<>(); 
        for(Question q : Question.getAllQuestions()){
            if (numQuestions == 0)
                return questions;

            if ((q.getCourse().equals(Course)) || (q.getTopic().equals(Topic)) || (q.getSubTopic().equals(subTopic)) || (q.getDifficulty().equals(difficulty)) ){
                questions.add(q);
                numQuestions = numQuestions-1;
            }
            
        }
        return null;
    }        

    public static void create_exam(int numQuestions, String Course, String Topic, String subTopic, String difficulty){
        Exam newExam = new Exam(numQuestions, Course, Topic, subTopic, difficulty);
        Exam.getAllExams().add(newExam);

    }

    public static Exam retrieveExam(int examID){
        for(Exam egg : Exam.getAllExams()){
            if (egg.getExamID() == examID)
            {
                return egg;
            }
        }
        return null;
    }

    public static Question retrieveQuestionFromExam(int questionID,int examID){
            Exam egg = retrieveExam(examID);
            for(Question q : egg.getQuestions()){
                if (q.getQuestionID()==questionID){
                    return q;
                }        
            }
            return null;
        }
    
        public static void addQuestionToExam(int questionID, int examID){
            Question q = retrieveQuestion(questionID);
            Exam egg = retrieveExam(examID);
    
            if (q== null || egg==null ){
                System.out.println("No question or exam");
            }
            else{
                egg.getQuestions().add(q);
                egg.setNumQuestions(egg.getNumQuestions()+1);
                q.setLastUsed(LocalDateTime.now());
                q.setTimesUsed(q.getTimesUsed()+1);
                egg.setLastEdited(LocalDateTime.now());
    
            }
        }
    
        public static void removeQuestionFromExam(int questionID, int examID){
            Question q = retrieveQuestionFromExam(questionID,examID);
        Exam egg = retrieveExam(examID);

        if (q== null || egg==null ){
            System.out.println("No question or exam");
        }
        else{
            egg.getQuestions().remove(q);
            egg.setNumQuestions(egg.getNumQuestions()-1);
            egg.setLastEdited(LocalDateTime.now());
            // q.setLastUsed(LocalDateTime.now());
        }
    }

    public void generateExam(int numQuestions, String Course, String Topic, String subTopic, String difficulty){
        Exam egg = new Exam(numQuestions, Course, Topic, subTopic, difficulty);
        ArrayList<Question> questions = retrieveQuestions(numQuestions, Course, Topic, subTopic, difficulty);
        egg.setQuestions(questions);
    }

}
