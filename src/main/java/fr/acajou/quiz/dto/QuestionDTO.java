package fr.acajou.quiz.dto;

import fr.acajou.quiz.domain.Category;
import fr.acajou.quiz.domain.Difficulty;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;

import java.util.List;
import java.util.UUID;

public record QuestionDTO(
        
        UUID uuid,

        @NotBlank(message = "Le champs value de la question dans QUESTION est obligatoire")
        String value,

        @Enumerated(EnumType.STRING)
        Difficulty difficulty,

        @Enumerated(EnumType.STRING)
        List<Category> categories
) {}
