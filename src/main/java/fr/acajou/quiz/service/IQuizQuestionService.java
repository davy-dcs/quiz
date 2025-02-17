package fr.acajou.quiz.service;

import fr.acajou.quiz.domain.QuizQuestion;
import fr.acajou.quiz.dto.quiz.QuizPlayResponse;
import fr.acajou.quiz.dto.quiz.QuizRequest;

import java.util.List;

public interface IQuizQuestionService {
    QuizPlayResponse initializer(QuizRequest quizRequest);
}
