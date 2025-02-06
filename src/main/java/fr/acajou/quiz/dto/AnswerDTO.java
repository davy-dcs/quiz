package fr.acajou.quiz.dto;

import jakarta.validation.constraints.NotBlank;

import java.util.UUID;

public record AnswerDTO(
        UUID uuid,

        @NotBlank(message = "Le nom est obligatoire")
        String value
) {}

