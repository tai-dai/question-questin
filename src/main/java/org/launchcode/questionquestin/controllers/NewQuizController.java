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
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.lang.Integer.parseInt;

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

            newQuiz.initializeQuiz(quizLength, newQuestions);

            Question nextQuestion = (newQuiz.getQuestions()).get(0);

            quizRepository.save(newQuiz);
            questionRepository.saveAll(newQuiz.getQuestions());

            model.addAttribute("quiz", newQuiz);
            model.addAttribute("question", nextQuestion);
            model.addAttribute("questionNum", nextQuestion.getQuestionNum());
            model.addAttribute("numQuestions", newQuiz.getNumQuestions());

            return "admin/createQuizQNAs";
        }

        @RequestMapping(value = {"/{quizId}/questions/{questionNum}of{numQuestions}", "/{quizId}/review"},
                method = { RequestMethod.GET, RequestMethod.POST })
        public String quizComplete(Model model,
                                   @PathVariable(required = false) Integer questionNum,
                                   @PathVariable(required = false) Integer numQuestions,
                                   @PathVariable int quizId, @RequestParam(required = false) Integer questionId,
                                   @RequestParam(required = false) String questionName,
                                   @RequestParam(required = false) String questionType,
                                   @RequestParam(required = false) String answerA,
                                   @RequestParam(required = false) String answerB,
                                   @RequestParam(required = false) String answerC,
                                   @RequestParam(required = false) String answerD,
                                   @RequestParam(required = false) String correctAns,
                                   @RequestParam(required = false) Boolean quizSetupComplete) {

            Optional<Quiz> resultQuiz = quizRepository.findById(quizId);
            Quiz quiz = resultQuiz.get();

            //TODO fix so that questions can be edited from the review button
            if (quiz.getSetupComplete() == false) {
                Optional<Question> resultQuest = questionRepository.findById(questionId);
                Question lastQuestion = resultQuest.get();

                //TODO question.setType(questionType)
                lastQuestion.setName(questionName);
                List<Answer> newAnswers = new ArrayList<>();
                List<String> answerNames = new ArrayList<>();
                answerNames.add(answerA);
                answerNames.add(answerB);
                answerNames.add(answerC);
                answerNames.add(answerD);

                int correctAnsIndex = Integer.parseInt(correctAns, 10);

                lastQuestion.initialize(questionName, newAnswers, answerNames, lastQuestion, correctAnsIndex);

                answerRepository.saveAll(lastQuestion.getAnswers());
                questionRepository.save(lastQuestion);
                quizRepository.save(quiz);

                model.addAttribute("quiz", quiz);

                for (Question question : quiz.getQuestions()) {
                    if (question.getSetupComplete() == false) {
                        model.addAttribute("question", question);
                        return "admin/createQuizQNAs";
                    }
                }
            }

            quiz.setSetupComplete(true);
            quizRepository.save(quiz);
            model.addAttribute("quiz", quiz);
            return "admin/reviewQuiz";
        }
    }
