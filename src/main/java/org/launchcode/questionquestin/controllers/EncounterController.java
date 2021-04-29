package org.launchcode.questionquestin.controllers;

import org.launchcode.questionquestin.models.*;
import org.launchcode.questionquestin.models.data.AnswerRepository;
import org.launchcode.questionquestin.models.data.QuestionRepository;
import org.launchcode.questionquestin.models.data.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("encounter")
public class EncounterController {

    @Autowired
    private QuizRepository quizRepository;
    @Autowired
    private QuestionRepository questionRepository;
    @Autowired
    private AnswerRepository answerRepository;

    @RequestMapping(value = "{questionId}", method =  { RequestMethod.GET, RequestMethod.POST })
    public String displayEncounter(Model model, @PathVariable String questionId, @RequestParam(required = false) String selectedAnswer){

        int questionIdResult = Integer.parseInt(questionId, 10);
        Optional<Question> optQuestion = questionRepository.findById(questionIdResult);

        if (optQuestion.isPresent()) {
            Question question = optQuestion.get();


            Boolean correct = question.getAnswered();


            int answerIdResult = Integer.parseInt(selectedAnswer, 10);

            if (selectedAnswer != null) {

                ArrayList<Integer> selectedAnswers = new ArrayList<>();
                selectedAnswers.add(answerIdResult);
                correct = question.checkAnswers(selectedAnswers);

                if (correct) {
                    question.setAnswered(correct);
                    questionRepository.save(question);
                }
            }
            model.addAttribute("correct", correct);
            model.addAttribute("question", question);
        }

        return "playerQuiz/encounters/testEncounter";
    }

}
