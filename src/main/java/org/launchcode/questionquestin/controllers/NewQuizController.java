package org.launchcode.questionquestin.controllers;

import org.launchcode.questionquestin.models.Answer;
import org.launchcode.questionquestin.models.Question;
import org.launchcode.questionquestin.models.Quiz;
import org.launchcode.questionquestin.models.data.AnswerRepository;
import org.launchcode.questionquestin.models.data.QuestionRepository;
import org.launchcode.questionquestin.models.data.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("newQuiz")
public class NewQuizController {

    @Autowired
    private QuizRepository quizRepository;
    @Autowired
    private QuestionRepository questionRepository;
    @Autowired
    private AnswerRepository answerRepository;

        @GetMapping("")
        public String createNewQuiz(){
            return "admin/createQuiz";
        }

        @PostMapping("create")
        public String createQuizQs(Model model, @RequestParam String quizName, @RequestParam String numQuestions){
            int quizLength = 16;
            if(numQuestions == "16"){
                quizLength = 16;
            }

            List<Question> newQuestions = new ArrayList<>();
            Quiz newQuiz = new Quiz(quizName, quizLength, newQuestions);

            int i = 0;
            while(i < quizLength){
                i++;
                List<Answer> newAnswers = new ArrayList<>();
                newQuestions.add(new Question("name", newAnswers, i, newQuiz));
            }
            //At this point, we have a quiz with the selected amount of questions. (Can only be 16 currently)
            //The questions each have the placeholder name "name," and an empty arraylist.
            model.addAttribute("quiz", newQuiz);

            return "admin/createQuizQNAs";
        }

        @PostMapping("questions")
        public String quizComplete(Model model,
                                   String questionType1, String questionName1, String answer1A, String isCorrect1A, String answer1B, String isCorrect1B, String answer1C, String isCorrect1C, String answer1D, String isCorrect1D){
            return "";
        }
    // requestparams: how do you take in 160 request params at once oh my god
    //questionType1 - questionType16
    //questionName1 - questionName16
    //answer1A - answer16D
    //isCorrect1A - isCorrect16D

}
