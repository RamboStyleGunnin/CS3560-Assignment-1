import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Student {

    private Random r = new Random();
    private String uniqueId; // will consist of students initials and n random digits ex)AZ-123, ex)YS-555
    private final int NUM_OF_DIGITS_IN_ID=3;
    private List<String> uniqueIds = new ArrayList<>();
    private String currentSubmission=""; //records the most recent submission by this student

    Student(){
        this.uniqueId=generateUniqueId();
    }

    public String getUniqueId(){
        return uniqueId;
    }

    public char giveAnswer(){  //student randomly chooses answer
        String candidateAnswers = "ABCD";
        char answer=candidateAnswers.charAt(r.nextInt(0,candidateAnswers.length()));

        //for selectAllThatApply questions, we make sure student selects unique answers
        while(currentSubmission.contains(Character.toString(answer))){
            answer=candidateAnswers.charAt(r.nextInt(0,candidateAnswers.length()));
        }
        currentSubmission+=answer;//record answer. If student changes his mind, we can remove this submission.
        return answer;
    }

    public String generateUniqueId(){
        String alphabet= "ABCDEFGHIJKLMNOPQRSTUVWXYZ";//to generate students initials for their unique ID
        String candidateId="";
        candidateId+=alphabet.charAt(r.nextInt(0,alphabet.length()));//assign students initials as prefix to uniqueID.
        candidateId+=alphabet.charAt(r.nextInt(0,alphabet.length())) +"-";

        for(int i=0;i< NUM_OF_DIGITS_IN_ID;i++) //try filling the ID with random digits
            candidateId+=r.nextInt(0,10);

        while(uniqueIds.contains(candidateId)) {//replace those digits if the ID already exists. Keep the initials.
            candidateId=candidateId.substring(0,2);
            for(int i=0;i<NUM_OF_DIGITS_IN_ID;i++)
                candidateId+=r.nextInt(0,10);
        }
        return candidateId;  //return a unique ID

    }

    public String getCurrentSubmission(){ //use this to remove previous submission from voting tally
        return currentSubmission;
    }

    public void clearCurrentSubmission(){ //use this before asking another question and when resubmitting an answer
        currentSubmission="";
    }
}

