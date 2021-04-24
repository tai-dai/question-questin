package org.launchcode.questionquestin.models;

import com.sun.istack.NotNull;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class QuizUser{

    //TODO: hook up to users

    @Id
    @GeneratedValue
    private int id;
    private Quiz selectedQuiz;
    //private User user;
    private double percentComplete = 0;
    private Boolean isComplete = false;

    public QuizUser(Quiz selectedQuiz) {
        this.selectedQuiz = selectedQuiz;
    }

    //Getters 'n' setters

    public int getId() { return id; }
    public Quiz getSelectedQuiz() { return selectedQuiz; }
    public double getPercentComplete() { return percentComplete; }
    public Boolean getComplete() { return isComplete; }

    public void setSelectedQuiz(Quiz selectedQuiz) { this.selectedQuiz = selectedQuiz; }
    public void setPercentComplete(double percentComplete) { this.percentComplete = percentComplete; }
    public void setComplete(Boolean complete) { isComplete = complete; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        QuizUser quizUser = (QuizUser) o;
        return id == quizUser.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public void resetQuiz (){
        this.setPercentComplete(0);
        this.setComplete(false);
    }
}
