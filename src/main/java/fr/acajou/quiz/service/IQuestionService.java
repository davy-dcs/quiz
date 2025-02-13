package fr.acajou.quiz.service;

import fr.acajou.quiz.domain.Question;
import fr.acajou.quiz.dto.QuestionAnswerDTO;
import fr.acajou.quiz.dto.QuestionDTO;

import java.util.UUID;

public interface IQuestionService {
    QuestionDTO createQuestion(QuestionDTO questionDTO);
    QuestionDTO updateQuestion(QuestionDTO questionDTO);
    QuestionDTO getQuestionbyUUID(UUID uuid);
    void deleteQuestionbyUUID(UUID uuid);
    Long getId(QuestionDTO questionDTO);
    Question getbyUUID(UUID uuid);
}
