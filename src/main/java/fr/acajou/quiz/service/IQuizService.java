package fr.acajou.quiz.service;

import fr.acajou.quiz.dto.QuizRequest;
import fr.acajou.quiz.dto.QuizResponse;

import java.util.UUID;

public interface IQuizService {
    QuizResponse post(QuizRequest quizRequest);
    String delete(UUID uuid);
}
