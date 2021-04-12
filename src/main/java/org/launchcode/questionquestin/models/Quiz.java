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
    private double percentComplete = 0;
    private Boolean isComplete = false;

    public Quiz(String name, List questions){
        this.setName(name);
        this.questions = questions;
    }

    public Quiz(){}
}
