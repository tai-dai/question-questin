package org.launchcode.questionquestin.models;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class Answer extends AbstractEntity{

    private Boolean isCorrect;

    @ManyToOne
    private Question question;

    public Answer(String name, Boolean isCorrect, Question question){
        this.setName(name);
        this.isCorrect = isCorrect;
        this.question = question;
    }

    public Answer(){}

//    Getters 'n' setters
    public Boolean getCorrect() { return isCorrect; }
    public Question getQuestion() { return question; }

    public void setCorrect(Boolean correct) { isCorrect = correct; }
    public void setQuestion(Question question) { this.question = question; }
}
