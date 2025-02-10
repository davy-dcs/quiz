package fr.acajou.quiz.dto;

import fr.acajou.quiz.domain.Answer;
import fr.acajou.quiz.domain.Question;
import jakarta.validation.constraints.AssertFalse;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record QuestionAnswerDTO(
        UUID uuid,

        Question question,

        Answer answer,

        boolean correct
) {}
