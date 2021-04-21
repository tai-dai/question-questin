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
            model.addAttribute("questionNum", nextQuestion.getQuestionNum());
            model.addAttribute("numQuestions", newQuiz.getNumQuestions());

            return "admin/createQuizQNAs";
        }

        @PostMapping("/{quizId}/questions/{questionNum}of{numQuestions}")
        public String quizComplete(Model model,
                                   @PathVariable int questionNum, @PathVariable int numQuestions,
                                   @PathVariable int quizId, @RequestParam int questionId,
                                   @RequestParam String questionName, @RequestParam String questionType,
                                   @RequestParam String answerA, @RequestParam String answerB,
                                   @RequestParam String answerC, @RequestParam String answerD,
                                   @RequestParam String correctAns, @RequestParam Boolean quizSetupComplete) {

            Optional<Quiz> resultQuiz = quizRepository.findById(quizId);
            Quiz quiz = resultQuiz.get();

            Optional<Question> resultQuest = questionRepository.findById(questionId);
            Question lastQuestion = resultQuest.get();

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
                selectedAnswer.setCorrect(true);
            } else if (correctAns == "ansB"){
                selectedAnswer = newAnswers.get(1);
                selectedAnswer.setCorrect(true);
            } else if (correctAns == "ansC"){
                selectedAnswer = newAnswers.get(2);
                selectedAnswer.setCorrect(true);
            } else if (correctAns == "ansD"){
                selectedAnswer = newAnswers.get(4);
                selectedAnswer.setCorrect(true);
            }

            lastQuestion.setAnswers((ArrayList<Answer>) newAnswers);
            lastQuestion.setSetupComplete(true);

            answerRepository.saveAll(newAnswers);
            questionRepository.save(lastQuestion);
            quizRepository.save(quiz);

            //check for complete setup in quiz

            if (quizSetupComplete == false) {
                //find the next question in quiz questions that has setupComplete = false
                for (Question question : quiz.getQuestions()) {
                    if (question.getSetupComplete() == false) {
                        model.addAttribute("quiz", quiz);
                        model.addAttribute("question", question);
                        return "admin/createQuizQNAs";
                    }
                }
                quiz.setSetupComplete(true);
            }

            //return a template with the printed quiz for review
            //questions can be clicked, which will redirect to the question for editing
            //add a new button to quizQNAs that says "finish," which will return to the review template
            return "index";
        }

//        @PostMapping("/reviewQuiz/{quizId}")
//        public String reviewQuiz(Model model, @PathVariable int quizId){
//
//            Optional<Quiz> resultQuiz = quizRepository.findById(quizId);
//            Quiz quiz = resultQuiz.get();
//
//
//        }
    }
