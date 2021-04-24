package org.launchcode.questionquestin.controllers;

import org.launchcode.questionquestin.models.Quiz;
import org.launchcode.questionquestin.models.data.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("quizControl")
public class quizControlDebugController {

    @Autowired
    private QuizRepository quizRepository;

    @RequestMapping(value = "", method =  { RequestMethod.GET, RequestMethod.POST })
    public String quizControl(Model model,
                              @RequestParam(required = false) Integer selectQuizId,
                              @RequestParam(required = false) Integer resetQuizId) {
        if (selectQuizId != null){
            Optional<Quiz> quiz = quizRepository.findById(selectQuizId);
            Quiz selectedQuiz = quiz.get();

            //selected quiz should be fed to the encounters through a DTO or something?

            Boolean selectSuccessful = true;
            model.addAttribute("selectSuccessful", selectSuccessful);
            model.addAttribute("selectedQuiz", selectedQuiz);
        }

        if (resetQuizId != null){
            Optional<Quiz> quiz = quizRepository.findById(resetQuizId);
            Quiz resettiedQuiz = quiz.get();

            //selected quiz should be fed to the encounters through a DTO or something?

            Boolean resetSuccessful = true;
            model.addAttribute("resetSuccessful", resetSuccessful);
            model.addAttribute("resettiedQuiz", resettiedQuiz);
        }

        return "playerQuiz/quizDebug";
    }

}
