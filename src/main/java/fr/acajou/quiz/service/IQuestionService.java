package fr.acajou.quiz.service;

import fr.acajou.quiz.domain.Question;
import fr.acajou.quiz.dto.QuestionDTO;

import java.util.UUID;

import fr.acajou.quiz.domain.Category;
import fr.acajou.quiz.domain.Difficulty;

import java.util.List;

public interface IQuestionService {
    QuestionDTO createQuestion(QuestionDTO questionDTO);
    QuestionDTO updateQuestion(QuestionDTO questionDTO);
    QuestionDTO getQuestionbyUUID(UUID uuid);
    void deleteQuestionbyUUID(UUID uuid);
    Long getId(QuestionDTO questionDTO);
    Question getbyUUID(UUID uuid);
    List<Question> find(Category category, Difficulty difficulty);
}
