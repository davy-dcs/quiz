package fr.acajou.quiz.service.impl;

import fr.acajou.quiz.domain.*;
import fr.acajou.quiz.dto.questionAnswer.QuestionAnswersResponse;
import fr.acajou.quiz.dto.quiz.QuizPlayResponse;
import fr.acajou.quiz.dto.quiz.QuizRequest;
import fr.acajou.quiz.repository.IQuizQuestionRepository;
import fr.acajou.quiz.repository.IQuizRepository;
import fr.acajou.quiz.service.IQuestionService;
import fr.acajou.quiz.service.IQuizQuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

@RequiredArgsConstructor
@Service
public class QuizQuestionServiceImpl implements IQuizQuestionService {
    private final IQuizQuestionRepository quizQuestionRepository;
    private final IQuestionService questionService;
    private final IQuizRepository quizRepository;

    @Override
    public QuizPlayResponse initializer(QuizRequest quizRequest) {
        List<Question> questions = questionService.find(quizRequest.category(), quizRequest.difficulty());

        Stack<Difficulty> difficulties = new Stack<>();
        while (questions.size() < quizRequest.numberOfQuestion() | difficulties.size() < 2){
            difficulties.push(quizRequest.difficulty());
            switch (difficulties.peek()) {
                case EASY, HARD -> {
                    difficulties.push(Difficulty.MEDIUM);
                    List<Question> questionAdditionalMediumList = questionService.find(quizRequest.category(), difficulties.peek());
                    questions.addAll(questionAdditionalMediumList);
                }
                case MEDIUM -> {
                    if (difficulties.contains(Difficulty.EASY)) {
                        difficulties.push(Difficulty.HARD);
                        List<Question> questionAdditionalHardList = questionService.find(quizRequest.category(), difficulties.peek());
                        questions.addAll(questionAdditionalHardList);
                    } else if (difficulties.contains(Difficulty.HARD)) {
                        difficulties.push(Difficulty.EASY);
                        List<Question> questionAdditionalEasyList = questionService.find(quizRequest.category(), difficulties.peek());
                        questions.addAll(questionAdditionalEasyList);
                    } else {
                        List<Question> easyList = questionService.find(quizRequest.category(), Difficulty.EASY);
                        List<Question> hardList = questionService.find(quizRequest.category(), Difficulty.HARD);
                        List<Question> biggerList;
                        List<Question> smallerList;
                        if (easyList.size() < hardList.size()) {
                            biggerList = hardList;
                            smallerList = easyList;
                        } else {
                            biggerList = easyList;
                            smallerList = hardList;
                        }
                        Collections.shuffle(biggerList);
                        Collections.shuffle(smallerList);
                        if ((easyList.size() + hardList.size()) >= (quizRequest.numberOfQuestion() - questions.size())) {
                            for (int i = 0; i < (quizRequest.numberOfQuestion() - questions.size()); i++) {
                                if (i % 2 == 0) {
                                    questions.add(biggerList.get(0));
                                    biggerList.remove(0);
                                } else {
                                    questions.add(smallerList.get(0));
                                    smallerList.remove(0);
                                }
                            }
                        } else {
                            questions.addAll(easyList);
                            difficulties.push(Difficulty.EASY);
                            questions.addAll(hardList);
                            difficulties.push(Difficulty.HARD);
                        }
                    }
                }
            }
        }
        Collections.shuffle(questions);
        Quiz quiz;
        List<Question> subList = questions;
        if (questions.size() >= quizRequest.numberOfQuestion()) {
            subList = questions.subList(0, quizRequest.numberOfQuestion() - 1);
            quiz = Quiz.builder()
                    .title(quizRequest.title())
                    .description(quizRequest.description())
                    .numberOfQuestions(quizRequest.numberOfQuestion())
                    .category(quizRequest.category())
                    .difficulty(quizRequest.difficulty()).build();
        } else {
            quiz = Quiz.builder()
                    .title(quizRequest.title())
                    .description(quizRequest.description())
                    .numberOfQuestions(questions.size())
                    .category(quizRequest.category())
                    .difficulty(quizRequest.difficulty()).build();
        }
        Quiz quizSaved = quizRepository.save(quiz);

        for (Question question : questions) {
            QuizQuestion quizQuestion = QuizQuestion.builder()
                    .question(question)
                    .quiz(quizSaved)
                    .build();
            quizQuestionRepository.save(quizQuestion);
        }

        return new QuizPlayResponse(quizSaved.getTitle(), quizSaved.getDescription(), quizSaved.getCategory(), quizSaved.getQuestions());
    }

}
