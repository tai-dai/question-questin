package org.launchcode.questionquestin.models;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Quiz extends AbstractEntity{

    @OneToMany
    @JoinColumn(name = "quiz_id")
    private List<Question> questions = new ArrayList<>();
    private int numQuestions;
    private double percentComplete = 0;
    private Boolean isComplete = false;

    public Quiz(String name, int numQuestions, List questions){
        this.numQuestions = numQuestions;
        this.setName(name);
        this.questions = questions;
    }

    public Quiz(){}

    public List<Question> getQuestions() { return questions; }
    public double getPercentComplete() { return percentComplete; }
    public Boolean getComplete() { return isComplete; }

    public void setQuestions(List<Question> questions) { this.questions = questions; }
    public void setPercentComplete(double percentComplete) { this.percentComplete = percentComplete; }
    public void setComplete(Boolean complete) { isComplete = complete; }
}
