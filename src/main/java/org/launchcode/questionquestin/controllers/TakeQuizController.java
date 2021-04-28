package org.launchcode.questionquestin.controllers;

import org.launchcode.questionquestin.models.Quiz;
import org.launchcode.questionquestin.models.data.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("takeQuiz")
public class TakeQuizController {

    @Autowired
    private QuizRepository quizRepository;

    @GetMapping("")
    public String displayTakeQuiz(Model model){
        List<Quiz> quizzes = quizRepository.findBySelected();

        if(quizzes.size() > 1){
            String quizError = "Too many quizzes are selected!";
            model.addAttribute("quizError", quizError);

        } else if (quizzes.get(0) != null){
            Quiz quiz = quizzes.get(0);

            model.addAttribute("quiz", quiz);

        } else {
            String quizError = "No quiz is here to take!";
            model.addAttribute("quizError", quizError);
        }

        //find selected quiz
        //if no quiz selected, return error
        //if quiz selected is valid,
            //run quiz.calculate complete, then save quiz
            //feed a list of quiz.questions
            //feed quiz percent complete into model
            //feed quiz iscomplete into model
        return "playerQuiz/takeQuiz";
    }

}
