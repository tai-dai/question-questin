package org.launchcode.questionquestin.controllers;

import org.launchcode.questionquestin.models.Question;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;

@Controller
@RequestMapping("encounter")
public class EncounterController {

    public ArrayList<String> testAnswers = new ArrayList<>();
    testAnswers.add("fuck");
    testAnswers.add("Nope!");
    testAnswers.add("It me");
    testAnswers.add("Don't look here!");
    Question testQuestion = new Question("What is the right answer?", testAnswers) ;

    @GetMapping("testEncounter")
    public String testEncounter(Model model){

    }
}
