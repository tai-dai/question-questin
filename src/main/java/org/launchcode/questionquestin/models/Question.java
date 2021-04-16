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

    private int questionNum;

//    TODO: add "question type" enums so questions can be multiple choice, true/false, or multiselect

    public Question(String name, List answers, int questionNum, Quiz quiz){
        this.setName(name);
        this.answers = answers;
        this.questionNum = questionNum;
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
    public int getQuestionNum() { return questionNum; }
    public Quiz getQuiz() { return quiz; }

    public void setAnswers(ArrayList<Answer> answers) { this.answers = answers; }
    public void setQuestionNum(int questionNum) { this.questionNum = questionNum; }
    public void setQuiz(Quiz quiz) { this.quiz = quiz; }

}
