package fr.acajou.quiz.service;

import fr.acajou.quiz.domain.Quiz;
import fr.acajou.quiz.dto.quiz.QuizPlayResponse;
import fr.acajou.quiz.dto.quiz.QuizRequest;
import fr.acajou.quiz.dto.quiz.QuizResponse;

import java.util.List;
import java.util.UUID;

public interface IQuizService {
    List<QuizResponse> getAll();
    QuizPlayResponse post(QuizRequest quizRequest);
    String delete(UUID uuid);
    Quiz getQuiz(UUID uuid);
}
