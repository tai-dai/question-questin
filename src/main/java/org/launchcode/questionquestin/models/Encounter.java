package org.launchcode.questionquestin.models;

import com.sun.istack.NotNull;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Encounter extends AbstractEntity{

    //private questor

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "encounter_id")
    private List<Question> questions = new ArrayList<>();

    @NotNull
    private int encounterNum;

    public Encounter(String name, List<Question> questions, int encounterNum) {
        this.setName(name);
        this.questions = questions;
        this.encounterNum = encounterNum;
    }

    //Getters 'n' setters
    public List<Question> getQuestions() { return questions; }
    public void setEncounterNum(int encounterNum) { this.encounterNum = encounterNum; }

    public void setQuestions(List<Question> questions) { this.questions = questions; }
    public int getEncounterNum() { return encounterNum; }
}
