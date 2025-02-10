package fr.acajou.quiz.dto.quiz;

import fr.acajou.quiz.domain.Category;
import fr.acajou.quiz.domain.Difficulty;

import java.util.UUID;

public record QuizResponse(
        UUID uuid,
        String title,
        String description,
        Integer numberOfQuestions,
        Category category,
        Difficulty difficulty
) {
}
