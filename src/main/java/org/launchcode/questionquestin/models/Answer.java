package org.launchcode.questionquestin.models;

import javax.persistence.Entity;

@Entity
public class Answer extends AbstractEntity{

    private Boolean isCorrect;

    public Answer(){}

    public Answer(String name, Boolean isCorrect){
        super();
        this.isCorrect = isCorrect;
    }

//    Getters 'n' setters
    public Boolean getCorrect() { return isCorrect; }

    public void setCorrect(Boolean correct) { isCorrect = correct; }
}
