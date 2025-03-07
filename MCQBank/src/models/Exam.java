package models;

import java.time.LocalDateTime;
import java.util.*;

public class Exam {
    private static ArrayList<Exam> allExams = new ArrayList<>(); 
    private ArrayList<Question> questions = new ArrayList<>();

    private static int xD = 0;
    private int ExamID;
  
    private int numQuestions;
    private String Course;
    private String Topic;
    private String subTopic;
    private String difficulty;

    private LocalDateTime dateCreated;
    private LocalDateTime lastUsed;
    private LocalDateTime lastEdited;
    

    public Exam(int numQuestions, String Course, String Topic, String subTopic, String difficulty){
        xD = xD + 1;
        this.ExamID = xD;
        this.numQuestions = numQuestions;
        this.Course = Course;
        this.Topic = Topic;
        this.subTopic = subTopic;
        this.difficulty = difficulty;
        this.dateCreated = LocalDateTime.now();
    }

    public static ArrayList<Exam> getAllExams(){
        return allExams;
    }

    // Getter and Setter for questions
    public ArrayList<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(ArrayList<Question> questions) {
        this.questions = questions;
    }

    // Getter and Setter for ExamID
    public int getExamID() {
        return ExamID;
    }

    // Getter and Setter for numQuestions
    public int getNumQuestions() {
        return numQuestions;
    }

    public void setNumQuestions(int numQuestions) {
        this.numQuestions = numQuestions;
    }

    // Getter and Setter for Course
    public String getCourse() {
        return Course;
    }

    public void setCourse(String course) {
        this.Course = course;
    }

    // Getter and Setter for Topic
    public String getTopic() {
        return Topic;
    }

    public void setTopic(String topic) {
        this.Topic = topic;
    }

    // Getter and Setter for subTopic
    public String getSubTopic() {
        return subTopic;
    }

    public void setSubTopic(String subTopic) {
        this.subTopic = subTopic;
    }

    // Getter and Setter for difficulty
    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    // Getter and Setter for dateCreated
    public LocalDateTime getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(LocalDateTime dateCreated) {
        this.dateCreated = dateCreated;
    }

    // Getter and Setter for lastUsed
    public LocalDateTime getLastUsed() {
        return lastUsed;
    }

    public void setLastUsed(LocalDateTime lastUsed) {
        this.lastUsed = lastUsed;
    }
    
    public LocalDateTime getLastEdited() {
        return lastEdited;
    }

    public void setLastEdited(LocalDateTime lastEdited) {
        this.lastEdited = lastEdited;
    }
}
