package fr.acajou.quiz.service;

import fr.acajou.quiz.domain.Answer;
import fr.acajou.quiz.dto.AnswerDTO;

import java.util.UUID;

public interface IAnswerService {
    AnswerDTO createAnswer(AnswerDTO answerDTO);
    AnswerDTO updateAnswer(AnswerDTO answerDTO);
    AnswerDTO getAnswerbyUUID(UUID uuid);
    void deleteAnswerbyUUID(UUID uuid);
    Long getId(AnswerDTO answerDTO);
    Answer getbyUUID(UUID uuid);
}
