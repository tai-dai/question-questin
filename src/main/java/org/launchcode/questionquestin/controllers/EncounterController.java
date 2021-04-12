package org.launchcode.questionquestin.controllers;

import org.launchcode.questionquestin.models.Answer;
import org.launchcode.questionquestin.models.Question;
import org.launchcode.questionquestin.models.data.AnswerRepository;
import org.launchcode.questionquestin.models.data.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("encounters")
public class EncounterController {

    @Autowired
    private QuestionRepository questionRepository;
    @Autowired
    private AnswerRepository answerRepository;

//    @GetMapping(value = "testEncounter")
//    public String initTestEncounter(Model model, @RequestParam(required = false) Integer selectedAnswer){
//
//        List<Answer> testAnswers = new ArrayList<>();
//        Question testQuestion = new Question("What is the right answer?", testAnswers);
//
//        questionRepository.save(testQuestion);
//
//        testAnswers.add(new Answer("Not I", false, testQuestion));
//        testAnswers.add(new Answer("Nope!", false, testQuestion));
//        testAnswers.add(new Answer("It me", true, testQuestion));
//        testAnswers.add(new Answer("Don't look here!", false, testQuestion));
//        answerRepository.saveAll(testAnswers);
//        questionRepository.save(testQuestion);
//
//        model.addAttribute("question", testQuestion);
//
//        return "playerQuiz/encounters/testEncounter";
//    }
//
//    @PostMapping(value = "testEncounter")
//    public String resultTestEncounter(Model model, @RequestParam(required = false) Integer selectedAnswer){
//
//        Boolean correct = false;
//
//        Optional<Question> optQuestion = questionRepository.findById(38);
//
//        if(optQuestion.isPresent()) {
//            Question testQuestion = (Question) optQuestion.get();
//            if (selectedAnswer != null) {
//                ArrayList<Integer> selectedAnswers = new ArrayList<>();
//                selectedAnswers.add(selectedAnswer);
//                correct = testQuestion.checkAnswers(selectedAnswers);
//
//                model.addAttribute("question", testQuestion);
//                model.addAttribute("correct", correct);
//            }
//
//            return "playerQuiz/encounters/testEncounter";
//        } else {
//            return "redirect";
//        }
//
//
//    }
}
