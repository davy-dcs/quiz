package fr.acajou.quiz.service;

import fr.acajou.quiz.dto.QuestionAnswerDTO;

import java.util.UUID;

public interface IQuestionAnswerService {
    QuestionAnswerDTO createQuestionAnswer(QuestionAnswerDTO questionAnswerDTO);
    QuestionAnswerDTO updateQuestionAnswer(QuestionAnswerDTO questionAnswerDTO);
    QuestionAnswerDTO getQuestionAnswerbyUUID(UUID uuid);
    void deleteQuestionAnswerbyUUID(UUID uuid);
    Long getId(QuestionAnswerDTO questionAnswerDTO);
}
