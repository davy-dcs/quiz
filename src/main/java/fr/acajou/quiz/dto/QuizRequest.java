package fr.acajou.quiz.dto;

import fr.acajou.quiz.domain.Category;
import fr.acajou.quiz.domain.Difficulty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record QuizRequest(
        @NotBlank
        String title,
        @NotBlank
        String description,
        @NotNull
        Integer numberOfQuestion,
        @NotNull
        Category category,
        @NotNull
        Difficulty difficulty
) {
}
