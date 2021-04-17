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

            return "admin/createQuizQNAs";
        }

        @PostMapping("questions/{questionNum}of{numQuestions}")
        public String quizComplete(Model model,
                                   @PathVariable int questionNum, @PathVariable int numQuestions,
                                   @RequestParam int quizId, @RequestParam int questionId,
                                   @RequestParam String questionName, @RequestParam String questionType,
                                   @RequestParam String answerA, @RequestParam String answerB,
                                   @RequestParam String answerC, @RequestParam String answerD,
                                   @RequestParam String correctAns, @RequestParam Boolean quizComplete) {

            Optional<Quiz> resultQuiz = quizRepository.findById(quizId);
            Quiz quiz = resultQuiz.get();

            Optional<Question> resultQuest = questionRepository.findById(questionId);
            Question lastQuestion = resultQuest.get();

            Question nextQuestion = new Question();

            //TODO question.setType(questionType)
            lastQuestion.setName(questionName);
            List<Answer> newAnswers = new ArrayList<>();
            newAnswers.add(new Answer(answerA, false, lastQuestion));
            newAnswers.add(new Answer(answerB, false, lastQuestion));
            newAnswers.add(new Answer(answerC, false, lastQuestion));
            newAnswers.add(new Answer(answerD, false, lastQuestion));

            Answer selectedAnswer;
            if (correctAns == "ansA"){
                selectedAnswer = newAnswers.get(0);
            } else if (correctAns == "ansB"){
                selectedAnswer = newAnswers.get(1);
            } else if (correctAns == "ansC"){
                selectedAnswer = newAnswers.get(2);
            } else if (correctAns == "ansD"){
                selectedAnswer = newAnswers.get(0);
            }

            lastQuestion.setAnswers((ArrayList<Answer>) newAnswers);

            if (quizComplete == false) {

                for (Question question : quiz.getQuestions()) {
                    if (question.getSetupComplete() == false) {
                        nextQuestion = question;
                        model.addAttribute("quiz", quiz);
                        model.addAttribute("question", question);
                        return "admin/createQuizQNAs";
                    } else {
                        continue;
                    }
                }
            }
            return "index";

            //so new plan
            //postMapping questions + (question #) of (# of questions)
            //parameters quiz, questionType, questionName, answerA, answerB, answerC, answerD, correctAns
            //loops through each question in quiz, setting properties, until all are completed
            //when all are completed, activate "finish" button
            //finish button leads to completed quiz, can link back to questions if they need editing.
            //when completed quiz is accepted by user, reroutes to all of user's quizzes
        }
    }
