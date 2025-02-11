package fr.acajou.quiz.service;

import fr.acajou.quiz.domain.Quiz;
import fr.acajou.quiz.dto.quiz.QuizRequest;
import fr.acajou.quiz.dto.quiz.QuizResponse;

import java.util.Optional;
import java.util.UUID;

public interface IQuizService {
    QuizResponse post(QuizRequest quizRequest);
    String delete(UUID uuid);
    Quiz getQuiz(UUID uuid);
}
