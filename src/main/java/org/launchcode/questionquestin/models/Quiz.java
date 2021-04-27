package org.launchcode.questionquestin.models;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Quiz extends AbstractEntity{

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "quiz_id")
    private List<Question> questions = new ArrayList<>();
    private int numQuestions;
    private Boolean setupComplete = false;
    private Boolean selected = false;
    private double percentComplete = 0;
    private Boolean isComplete = false;

    public Quiz(String name, int numQuestions, List questions){
        this.numQuestions = numQuestions;
        this.setName(name);
        this.questions = questions;
    }

    public Quiz(){}

    public void initializeQuiz(int quizLength, List newQuestions){
        int i = 0;
        while(i < quizLength){
            i++;
            List<Answer> newAnswers = new ArrayList<>();
            newQuestions.add(new Question("name", newAnswers, i, this));
        }
    }

    public double calculateComplete(){
        int answeredQs = 0;
        for (Question question : questions){
            if (question.getAnswered() == true){
                answeredQs ++;
            }
        }

        double percentage = answeredQs % this.numQuestions;

        this.percentComplete = percentage;
        return percentage;
    }

    //TODO delete when users
    public void resetQuiz (){
        for (Question question : questions){
            question.setAnswered(false);
        }
        this.setPercentComplete(0);
        this.setComplete(false);
    }

    public List<Question> getQuestions() { return questions; }
    public Boolean getSetupComplete() { return setupComplete; }
    public int getNumQuestions() { return numQuestions; }
    public double getPercentComplete() { return percentComplete; }
    public Boolean getComplete() { return isComplete; }
    public Boolean getSelected() { return selected; }

    public void setQuestions(List<Question> questions) { this.questions = questions; }
    public void setSetupComplete(Boolean setupComplete) { this.setupComplete = setupComplete; }
    public void setNumQuestions(int numQuestions) { this.numQuestions = numQuestions; }
    public void setPercentComplete(double percentComplete) { this.percentComplete = percentComplete; }
    public void setComplete(Boolean complete) { isComplete = complete; }
    public void setSelected(Boolean selected) { this.selected = selected; }

}
