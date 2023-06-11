import java.util.List;
import java.util.Random;

public class VotingService {
    private int answeredA=0;
    private int answeredB=0;
    private int answeredC=0;
    private int answeredD=0;
    private int questionNumber=1; //counts how many questions being asked
    private int studentsWhoGaveOneAnswer=0;
    private int studentsWhoGaveTwoAnswers=0;
    private int studentsWhoGaveThreeAnswers=0;
    private int studentsWhoGaveFourAnswers=0;
    private int submittedMoreThanOnce=0; //counts how many times students submitted more than once

    VotingService(){
    }

    //each student gives 1 answer
    public void beginPolling(SingleChoiceQuestion question, List<Student>students) {
        System.out.println("\n\nQuestion "+questionNumber+":");
        displayQuestion(question);

        for (Student student: students) { //get the answer from each student

            for(int i=0;i<2;i++) { //running this twice to simulate students changing their minds
                if (student.getCurrentSubmission() != "") {//check if student already submitted for this question
                    ensureOnlyOneSubmission(student); //clear the previous submission
                }
                tallyTheResults(student.giveAnswer());//student submits their answer
            }
        }
        displayResults();
        resetAnswers(students); //reset everything for next question
        questionNumber++;
    }

    //student can select 1, 2, 3, or 4 answers
    public void beginPolling(SelectAllThatApply question, List<Student>students) {
        System.out.println("\n\nQuestion "+questionNumber+":");
        displayQuestion(question);
        Random r = new Random();

        for(Student student:students){  //get the answer(s) from each student

            for(int i=0;i<2;i++) { //running this twice to simulate students changing their minds
                int answersToBeSelected = r.nextInt(1,5); //determine how many options student will select

                if (student.getCurrentSubmission() != "") {//check if student already submitted for this question
                    ensureOnlyOneSubmission(student); //clear the previous submission
                }

                for(int j=0;j<answersToBeSelected;j++){ //student gives their answer(s)
                    tallyTheResults(student.giveAnswer());
                }

                //keep track of how many students chose multiple answers
                if(answersToBeSelected==1)
                    studentsWhoGaveOneAnswer++;
                else if(answersToBeSelected==2)
                    studentsWhoGaveTwoAnswers++;
                else if(answersToBeSelected==3)
                    studentsWhoGaveThreeAnswers++;
                else
                    studentsWhoGaveFourAnswers++;
           }
        }
        displayResults();

        //print how many students selected multiple options (only for SelectAllThatApply type questions)
        System.out.println("\n"+studentsWhoGaveOneAnswer+" students only selected 1 option.");
        System.out.println(studentsWhoGaveTwoAnswers+" students selected 2 options.");
        System.out.println(studentsWhoGaveThreeAnswers+" students selected 3 options.");
        System.out.println(studentsWhoGaveFourAnswers+" students selected all 4 options.");
        resetAnswers(students);
        questionNumber++;
    }

    private void displayQuestion(IQuestion question){ //print the question and candidate answers
        System.out.println(question.askTheQuestion());
        System.out.println(question.giveFourPossibleSolutions());
    }

    private void tallyTheResults(char answerGiven){ //count how many voted for each answer
        if(answerGiven=='A')
            answeredA++;
        else if(answerGiven=='B')
            answeredB++;
        else if(answerGiven=='C')
            answeredC++;
        else
            answeredD++;
    }

    private void displayResults(){
        System.out.println("\n     Results are displayed below\n-------------------------------------");
        System.out.println(answeredA+" students selected A");
        System.out.println(answeredB+" students selected B");
        System.out.println(answeredC+" students selected C");
        System.out.println(answeredD+" students selected D");
        if(submittedMoreThanOnce>0) {    //display this only if there was a resubmission
            System.out.println("\nStudents changed their answer " + submittedMoreThanOnce + " time(s)." +
                    " Only the last submission was used for these results.");
        }
    }

    private void ensureOnlyOneSubmission(Student student){ //remove any previous submission tally
        if (student.getCurrentSubmission().contains("A")) {
            answeredA--;
        }
        if (student.getCurrentSubmission().contains("B")) {
            answeredB--;
        }
        if (student.getCurrentSubmission().contains("C")) {
            answeredC--;
        }
        if (student.getCurrentSubmission().contains("D")){
            answeredD--;
        }
        if(student.getCurrentSubmission().length()==1){
            studentsWhoGaveOneAnswer--;
        }
        else if(student.getCurrentSubmission().length()==2){
            studentsWhoGaveTwoAnswers--;
        }
        else if(student.getCurrentSubmission().length()==3){
            studentsWhoGaveThreeAnswers--;
        }
        else{
            studentsWhoGaveFourAnswers--;
        }
        student.clearCurrentSubmission(); //remove submission history from student object
        submittedMoreThanOnce++; //count a resubmission
    }

    private void resetAnswers(List<Student>students){
        answeredA=0;
        answeredB=0;
        answeredC=0;
        answeredD=0;
        studentsWhoGaveOneAnswer=0;
        studentsWhoGaveTwoAnswers=0;
        studentsWhoGaveThreeAnswers=0;
        studentsWhoGaveFourAnswers=0;
        submittedMoreThanOnce=0;
        for(Student student: students){
            student.clearCurrentSubmission();
        }
    }

    public void displayParticipants(int numOfStudents){ //print how many students will vote
        System.out.println("\n\n"+numOfStudents+" students are participating.");
    }

}
