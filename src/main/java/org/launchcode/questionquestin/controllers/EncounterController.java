package org.launchcode.questionquestin.controllers;

import org.launchcode.questionquestin.models.Answer;
import org.launchcode.questionquestin.models.Question;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@Controller
@RequestMapping("encounter")
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

//    @PostMapping("testEncounter")
//    public String processAnswer(@RequestParam int ans) {
//
//        return "playerQuiz/encounters/testEncounter";
//    }
}
