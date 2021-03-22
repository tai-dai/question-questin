package org.launchcode.questionquestin.models;


import javax.persistence.Entity;
import java.util.ArrayList;

@Entity
public class Question extends AbstractEntity{

    private ArrayList<Answer> answers = new ArrayList<>();

    public Question(){}

    public Question(String name, ArrayList answers){
        super();
        this.setName(name);
        this.answers = answers;
    }
    
    public boolean checkAnswer(Answer selectedAnswer){
        if (selectedAnswer.getCorrect() == true){
            return true;
        }
        return false;
    }

//    Getters 'n' setters

    public ArrayList<Answer> getAnswers() { return answers; }

    public void setAnswers(ArrayList<Answer> answers) { this.answers = answers; }

}
