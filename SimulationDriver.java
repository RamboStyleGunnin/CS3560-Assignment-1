import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SimulationDriver {

    public static void main(String[] args) {
        final int MAX_CLASS_SIZE=34;
        final int MIN_CLASS_SIZE=24;
        List<Student> students = new ArrayList<>();

        generateStudents(students, MIN_CLASS_SIZE, MAX_CLASS_SIZE); //create the students

        IQuestion q1 = new SingleChoiceQuestion(); //creates instance of single choice question
        IQuestion q2 = new SelectAllThatApply(); //create instance of multiple selection question
        generateQuestions(q1, q2); //simulate writing the questions and the possible answers

        VotingService personalityQuiz = new VotingService(); //create instance of voting service

        //start simulation
        personalityQuiz.displayParticipants(students.toArray().length); //display number of participants
        personalityQuiz.beginPolling((SingleChoiceQuestion)q1,students);//configure the voting service with question 1
        personalityQuiz.beginPolling((SelectAllThatApply) q2,students);//configure the voting service with question 2
        //end of simulation
    }

    private static void generateStudents(List<Student> students,int min, int max){
        Random r = new Random();
        int numOfStudents = r.nextInt(min, max+1);//determine how many students will vote

        for(int i=0;i<numOfStudents;i++) { //generate the student objects
            students.add(new Student());
        }
    }

    private static void generateQuestions(IQuestion q1, IQuestion q2){

            q1.setTheQuestion("How do you prefer to play video games?");
            q1.setAnswerA("Nintendo");
            q1.setAnswerB("Playstation");
            q1.setAnswerC("Xbox");
            q1.setAnswerD("Pc");

            q2.setTheQuestion("Build your ideal pizza.");
            q2.setAnswerA("Pepperoni");
            q2.setAnswerB("Pineapple");
            q2.setAnswerC("Sausage");
            q2.setAnswerD("Jalapeno");
    }
}