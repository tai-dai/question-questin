package org.launchcode.questionquestin.models;


import javax.persistence.Entity;
import java.util.ArrayList;
import java.util.Collections;

@Entity
public class Question extends AbstractEntity{

    private ArrayList<Answer> answers = new ArrayList<>();

    public Question(){}

    public Question(String name, ArrayList answers){
        super();
        this.setName(name);
        this.answers = answers;
    }
    
    public boolean checkAnswers(ArrayList<Integer> selectedAnswers){
        ArrayList<Integer> correctAnswers = new ArrayList<>();

        for (Answer answer : this.answers){
            if (answer.getCorrect() == true) {
                correctAnswers.add(answer.getId());
            }
        }

        Collections.sort(selectedAnswers);
        Collections.sort(correctAnswers);

        if (correctAnswers.equals(selectedAnswers)) {
            return true;
        }

        return false;
    }

//    Getters 'n' setters

    public ArrayList<Answer> getAnswers() { return answers; }

    public void setAnswers(ArrayList<Answer> answers) { this.answers = answers; }

}
