package org.launchcode.questionquestin.models;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Entity
public class Question extends AbstractEntity {

    @ManyToOne
    private Quiz quiz;

    @OneToMany
    @JoinColumn(name = "question_id")
    private List<Answer> answers = new ArrayList<>();

    public Question(String name, List answers, Quiz quiz){
        this.setName(name);
        this.answers = answers;
        this.quiz = quiz;
    }

    public Question(){}
    
    public boolean checkAnswers(List<Integer> selectedAnswers){
        List<Integer> correctAnswers = new ArrayList<>();

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

    public List<Answer> getAnswers() { return answers; }
    public Quiz getQuiz() { return quiz; }

    public void setAnswers(ArrayList<Answer> answers) { this.answers = answers; }
    public void setQuiz(Quiz quiz) { this.quiz = quiz; }

}
