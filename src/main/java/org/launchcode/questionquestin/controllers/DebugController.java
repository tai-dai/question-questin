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
public class DebugController {

    @Autowired
    private QuizRepository quizRepository;

    @RequestMapping(value = "", method = {RequestMethod.GET, RequestMethod.POST})
    public String quizControl(Model model,
                              @RequestParam(required = false) Integer selectQuizId,
                              @RequestParam(required = false) Integer resetQuizId) {
        if (selectQuizId != null) {

            String selectStatus = "That quiz isn't valid!";

            Optional<Quiz> resultQuizA = quizRepository.findById(selectQuizId);
            if (resultQuizA.isPresent()) {
                Quiz selectedQuiz = resultQuizA.get();
                if (selectedQuiz.getSetupComplete() != true) {
                    selectStatus = selectedQuiz.getName() + " isn't completely setup!";

                } else {
                    selectedQuiz.setSelected(true);

                    quizRepository.save(selectedQuiz);
                    selectStatus = selectedQuiz.getName() + " has been selected!";
                }

                model.addAttribute("selectedQuiz", selectedQuiz);
            }
            model.addAttribute("selectStatus", selectStatus);
        }

        if (resetQuizId != null) {
            Optional<Quiz> resultQuizB = quizRepository.findById(resetQuizId);
            Quiz resettiedQuiz = resultQuizB.get();
            if (resultQuizB.isPresent()) {

                //TODO this is a super basic way to store quiz progress

                resettiedQuiz.setSelected(false);
                resettiedQuiz.resetQuiz();

                Boolean resetSuccessful = true;

                quizRepository.save(resettiedQuiz);
                model.addAttribute("resetSuccessful", resetSuccessful);
                model.addAttribute("resettiedQuiz", resettiedQuiz);
            }
        }

        return "playerQuiz/quizDebug";
    }
}
