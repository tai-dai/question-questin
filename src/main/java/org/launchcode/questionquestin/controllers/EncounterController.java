package org.launchcode.questionquestin.controllers;

import org.launchcode.questionquestin.models.Answer;
import org.launchcode.questionquestin.models.Question;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@Controller
@RequestMapping("encounters")
public class EncounterController {

    @RequestMapping(value = "testEncounter", method = {RequestMethod.GET, RequestMethod.POST})
    public String testEncounter(Model model, @RequestParam(required = false) Integer selectedAnswer){

        ArrayList<Answer> testAnswers = new ArrayList<>();
        Boolean correct = false;

        testAnswers.add(new Answer("Not I", false));
        testAnswers.add(new Answer("Nope!", false));
        testAnswers.add(new Answer("It me", true));
        testAnswers.add(new Answer("Don't look here!", false));
        Question testQuestion = new Question("What is the right answer?", testAnswers) ;

        model.addAttribute("question", testQuestion);

        if (selectedAnswer != null) {
//            TODO: this currently always evaluates to true
            ArrayList<Integer> selectedAnswers = new ArrayList<>();
            selectedAnswers.add(selectedAnswer);
            correct = testQuestion.checkAnswers(selectedAnswers);

            model.addAttribute("correct", correct);
        }

        return "playerQuiz/encounters/testEncounter";
    }
}
