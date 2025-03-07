package models;

import java.awt.Image;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Question{
    private static ArrayList<Question> allQuestions = new ArrayList<>();

    private static int xD = 0;
    private int questionID;
    private String quest;
    private String Canswer;
    private String Wanswer1, Wanswer2, Wanswer3;
    private String Course;
    private String Topic, subTopic;
    private String difficulty;
    private LocalDateTime dateCreated;
    private LocalDateTime lastUsed;
    private int timesUsed;
    private float performanceMetric;
    private Image img;
    

    public Question(String quest,String Canswer, String Wanswer1, String Wanswer2, String Wanswer3, String Course, String Topic, String subTopic, String difficulty){
        xD = xD + 1;
        this.questionID = xD;
        this.quest = quest;
        this.Canswer = Canswer;
        this.Wanswer1 = Wanswer1;
        this.Wanswer2 = Wanswer2;
        this.Wanswer3 = Wanswer3;
        this.Course = Course;
        this.Topic = Topic;
        this.subTopic = subTopic;
        this.difficulty = difficulty;
        this.dateCreated = LocalDateTime.now();
        //allQuestions.add(this);
    }

    public Question(String quest,String Canswer, String Wanswer1, String Wanswer2, String Wanswer3, String Course, String Topic, String subTopic, String difficulty, Image img){
        this.questionID = questionID;
        this.quest = quest;
        this.Canswer = Canswer;
        this.Wanswer1 = Wanswer1;
        this.Wanswer2 = Wanswer2;
        this.Wanswer3 = Wanswer3;
        this.Course = Course;
        this.Topic = Topic;
        this.subTopic = subTopic;
        this.difficulty = difficulty;
        this.dateCreated = LocalDateTime.now();
        this.img = img;
    }

    public static ArrayList<Question> getAllQuestions(){
        return allQuestions;
    }

    // Getters and Setters
    public int getQuestionID(){
        return questionID;
    }

    public String getQuest() {
        return quest;
    }

    public void setQuest(String quest) {
        this.quest = quest;
    }

    public String getCanswer() {
        return Canswer;
    }

    public void setCanswer(String Canswer) {
        this.Canswer = Canswer;
    }

    public String getWanswer1() {
        return Wanswer1;
    }

    public void setWanswer1(String Wanswer1) {
        this.Wanswer1 = Wanswer1;
    }

    public String getWanswer2() {
        return Wanswer2;
    }

    public void setWanswer2(String Wanswer2) {
        this.Wanswer2 = Wanswer2;
    }

    public String getWanswer3() {
        return Wanswer3;
    }

    public void setWanswer3(String Wanswer3) {
        this.Wanswer3 = Wanswer3;
    }

    public String getCourse() {
        return Course;
    }

    public void setCourse(String Course) {
        this.Course = Course;
    }

    public String getTopic() {
        return Topic;
    }

    public void setTopic(String Topic) {
        this.Topic = Topic;
    }

    public String getSubTopic() {
        return subTopic;
    }

    public void setSubTopic(String subTopic) {
        this.subTopic = subTopic;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public Image getImg() {
        return img;
    }

    public void setImg(Image img) {
        this.img = img;
    }

    public LocalDateTime getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(LocalDateTime dateCreated) {
        this.dateCreated = dateCreated;
    }

    public LocalDateTime getLastUsed() {
        return lastUsed;
    }

    public void setLastUsed(LocalDateTime lastUsed) {
        this.lastUsed = lastUsed;
    }

    public int getTimesUsed(){
        return timesUsed;
    }

    public void setTimesUsed(int timesUsed){
        this.timesUsed = timesUsed;
    }

    public float getPerformanceMetric(){
        return performanceMetric;
    }

    public void setPerformanceMetric(float performanceMetric){
        this.performanceMetric = performanceMetric;
    }

}