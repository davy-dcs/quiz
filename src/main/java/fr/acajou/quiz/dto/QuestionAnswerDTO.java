package fr.acajou.quiz.dto;

import jakarta.validation.constraints.AssertFalse;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record QuestionAnswerDTO(
        UUID Id,

        @NotNull
        @AssertFalse
        boolean correct
) {}
