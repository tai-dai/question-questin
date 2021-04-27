package org.launchcode.questionquestin.models;

import com.sun.istack.NotNull;

import javax.persistence.CascadeType;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class Encounter{

    //private questor

    private final int id;
    private String name;
    private List<Question> questions = new ArrayList<>();
    private EncounterType encounterType;

    public Encounter(int id, String name, EncounterType encounterType) {
        this.id = id;
        this.name = name;
        this.encounterType = encounterType;
    }

    //Getters 'n' setters

    public int getId() { return id; }
    public String getName() { return name; }
    public List<Question> getQuestions() { return questions; }
    public EncounterType getEncounterType() { return encounterType; }

    public void setName(String name) { this.name = name; }
    public void setQuestions(List<Question> questions) { this.questions = questions; }
    public void setEncounterType(EncounterType encounterType) { this.encounterType = encounterType; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Encounter encounter = (Encounter) o;
        return id == encounter.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
