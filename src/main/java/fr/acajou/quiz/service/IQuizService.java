package fr.acajou.quiz.service;

import fr.acajou.quiz.dto.quiz.QuizRequest;
import fr.acajou.quiz.dto.quiz.QuizResponse;

import java.util.UUID;

public interface IQuizService {
    QuizResponse post(QuizRequest quizRequest);
    String delete(UUID uuid);
}
