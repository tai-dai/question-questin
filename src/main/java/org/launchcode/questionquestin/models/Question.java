package org.launchcode.questionquestin.models;


import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.ArrayList;
import java.util.Objects;

public class Question {

    private int id;
    private static int nextId = 1;
    private String name;
    private ArrayList<String> answers = new ArrayList<>();

    public Question() {
        id = nextId;
        nextId++;
    }

    public Question(String name, ArrayList answers){
        this.name = name;
        this.answers = answers;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public ArrayList<String> getAnswers() { return answers; }

    public void setName(String name) { this.name = name; }
    public void setAnswers(ArrayList<String> answers) { this.answers = answers; }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Question that = (Question) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
