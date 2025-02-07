package fr.acajou.quiz.dto;

import fr.acajou.quiz.domain.Category;
import fr.acajou.quiz.domain.Difficulty;

public record QuizResponse(
        String title,
        String description,
        Integer numberOfQuestions,
        Category category,
        Difficulty difficulty
) {
}
