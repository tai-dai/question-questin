package org.launchcode.questionquestin.models;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Entity
public class Question extends AbstractEntity {

    @ManyToOne
    private Quiz quiz;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "question_id")
    private List<Answer> answers = new ArrayList<>();

    private int questionNum;
    private Boolean setupComplete = false;

//    TODO: add "question type" enums so questions can be multiple choice, true/false, or multiselect

    public Question(String name, List answers, int questionNum, Quiz quiz) {
        this.setName(name);
        this.answers = answers;
        this.questionNum = questionNum;
        this.quiz = quiz;
    }

    public Question() {
    }

//    Getters 'n' setters

    public List<Answer> getAnswers() {
        return answers;
    }

    public int getQuestionNum() {
        return questionNum;
    }

    public Quiz getQuiz() {
        return quiz;
    }

    public Boolean getSetupComplete() {
        return setupComplete;
    }

    public void setAnswers(ArrayList<Answer> answers) {
        this.answers = answers;
    }

    public void setQuestionNum(int questionNum) {
        this.questionNum = questionNum;
    }

    public void setQuiz(Quiz quiz) {
        this.quiz = quiz;
    }

    public void setSetupComplete(Boolean setupComplete) {
        this.setupComplete = setupComplete;
    }

    //Class methods

    public boolean checkAnswers(List<Integer> selectedAnswers) {
        List<Integer> correctAnswers = new ArrayList<>();

        for (Answer answer : this.answers) {
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

    public void initialize(String questionName, List newAnswers, List answerNames, Question question, int correctAns) {
        this.setName(questionName);
        int i = 0;

        while (i < answerNames.size()) {
            String answerName = String.valueOf(answerNames.get(i));
            newAnswers.add(new Answer(answerName, false, question));
            i++;
        }

        this.setAnswers((ArrayList<Answer>) newAnswers);

        Answer selectedAnswer;
        selectedAnswer = this.getAnswers().get(correctAns);
        selectedAnswer.setCorrect(true);

        this.setupComplete = true;
    }
}

