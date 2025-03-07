import controllers.*;
import models.*;
import views.*;


public class App {
    public static void main(String[] args) throws Exception {
        //Question model = 
        //retrieveQuestionsFromDatabase();
        // QuestionView view = new QuestionView();
        // QuestionController controller = new QuestionController(model, view);
        
        QuestionController.create_question("What is the capital of France?","Paris", "Berlin","Madrid","Rome", "Geography", "Geography", "Countries", "Easy");
        QuestionController.create_question("What is the chemical symbol for gold?","Au","Ag", "Pb", "Fe", "Chemistry", "Periodic Table", "Elements", "Medium");
        QuestionController.create_question("Which planet is known as the Red Planet?", "Mars", "Venus", "Jupiter", "Saturn", "Astronomy", "Solar System", "Planets", "Easy");

        QuestionView.printAllQuestions(Question.getAllQuestions());
        QuestionView.printQuestion(2);
        QuestionController.edit_question(2, new Question("In what year did World War II end?", "1945", "1939",    "1918", "1950", "History", "World War II", "Major Events", "Hard"));
        QuestionView.printQuestion(2);
        QuestionController.delete_question(2);
        
        QuestionView.printAllQuestions(Question.getAllQuestions());


        ExamController.create_exam(0, "COMP2600", "Topic", "subTopic", "ifficulty");
        ExamView.printExam(1);
        System.out.println("HALLOOOOOOOO");
        ExamController.addQuestionToExam(1,1);
        System.out.println("HALLOOOOOOOO");
        ExamView.printExam(1);
        ExamController.addQuestionToExam(3,1);
        ExamView.printExam(1);
        ExamController.removeQuestionFromExam(1,1);
        ExamView.printExam(1);
        //Question question5 = new Question("In what year did World War II end?", "1945", "1939",    "1918", "1950", "History", "World War II", "Major Events", "Hard");

        // for(Question q : Question.getAllQuestions()){
        //     System.out.println(q.getQuestionID());
        // }
        //         //System.out.println(models.Question.getAllQuestions());
                //controller.updateView();
    }
        

        
    //     private static void retrieveQuestionsFromDatabase(){
        
    //     Question question = new Question("What is the capital of France?","Paris", "Berlin","Madrid","Rome", "Geography", "Geography", "Countries", "Easy");
    //     Question question2 = new Question("What is the chemical symbol for gold?","Au","Ag", "Pb", "Fe", "Chemistry", "Periodic Table", "Elements", "Easy");
    //     Question question3 = new Question("Which planet is known as the Red Planet?", "Mars", "Venus", "Jupiter", "Saturn", "Astronomy", "Solar System", "Planets", "Easy");

    // }


}
