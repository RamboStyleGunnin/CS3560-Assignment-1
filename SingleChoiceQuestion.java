public class SingleChoiceQuestion implements IQuestion {

    String questionBody="";
    String answerA="";
    String answerB="";
    String answerC="";
    String answerD="";

    SingleChoiceQuestion(){

    }

    public String askTheQuestion() {

        return questionBody+"\n(Select Best Answer)";
    }

    public String giveFourPossibleSolutions() {

        return "A) "+answerA+"\nB) "+answerB+"\nC) "+answerC+"\nD) "+answerD;
    }

    public void setTheQuestion(String questionBody) {
        this.questionBody=questionBody;
    }

    public void setAnswerA(String answerA) {
        this.answerA=answerA;
    }

    public void setAnswerB(String answerB) {
        this.answerB=answerB;
    }

    public void setAnswerC(String answerC) {
        this.answerC=answerC;
    }

    public void setAnswerD(String answerD) {
        this.answerD=answerD;
    }
}
