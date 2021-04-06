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

    @GetMapping("testEncounter")
    public String testEncounter(Model model){

        ArrayList<Answer> testAnswers = new ArrayList<>();
        testAnswers.add(new Answer("Not I", false));
        testAnswers.add(new Answer("Nope!", false));
        testAnswers.add(new Answer("It me", true));
        testAnswers.add(new Answer("Don't look here!", false));
        Question testQuestion = new Question("What is the right answer?", testAnswers) ;

        model.addAttribute("question", testQuestion);

        return "playerQuiz/encounters/testEncounter";
    }

    @PostMapping("testEncounter")
    public String processAnswer(Model model, @RequestParam Integer selectedAnswer) {

//        take in all selected answers, store them as Integer objects in ArrayList selected Answers
        ArrayList<Answer> testAnswers = new ArrayList<>();
        testAnswers.add(new Answer("Not I", false));
        testAnswers.add(new Answer("Nope!", false));
        testAnswers.add(new Answer("It me", true));
        testAnswers.add(new Answer("Don't look here!", false));
        Question testQuestion = new Question("What is the right answer?", testAnswers) ;

        ArrayList<Integer> selectedAnswers = new ArrayList<>();
        selectedAnswers.add(selectedAnswer);

        Boolean correct = false;

        correct = testQuestion.checkAnswers(selectedAnswers);

        model.addAttribute("question", testQuestion);
        model.addAttribute("correct", correct);

        return "playerQuiz/encounters/testEncounter";
    }
}
