package org.launchcode.questionquestin.controllers;

import org.launchcode.questionquestin.models.Quiz;
import org.launchcode.questionquestin.models.QuizUser;
import org.launchcode.questionquestin.models.data.QuizRepository;
import org.launchcode.questionquestin.models.data.QuizUserRepository;
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

    @Autowired
    private QuizUserRepository quizUserRepository;

    @RequestMapping(value = "", method =  { RequestMethod.GET, RequestMethod.POST })
    public String quizControl(Model model,
                              @RequestParam(required = false) Integer selectQuizId,
                              @RequestParam(required = false) Integer resetQuizId) {
        if (selectQuizId != null){
            Optional<Quiz> quiz = quizRepository.findById(selectQuizId);
            Quiz selectedQuiz = quiz.get();

            selectedQuiz.setSelected(true);

            Boolean selectSuccessful = true;
            model.addAttribute("selectSuccessful", selectSuccessful);
            model.addAttribute("selectedQuiz", selectedQuiz);
        }

        if (resetQuizId != null){
            Optional<Quiz> quiz = quizRepository.findById(resetQuizId);
            Quiz resettiedQuiz = quiz.get();

            //TODO this is a super basic way to store quiz progress

            resettiedQuiz.setSelected(false);
            resettiedQuiz.resetQuiz();

            Boolean resetSuccessful = true;
            model.addAttribute("resetSuccessful", resetSuccessful);
            model.addAttribute("resettiedQuiz", resettiedQuiz);
        }

        return "playerQuiz/quizDebug";
    }

}
