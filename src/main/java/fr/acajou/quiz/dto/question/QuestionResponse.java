package fr.acajou.quiz.dto.question;

import fr.acajou.quiz.domain.Difficulty;

import java.util.UUID;

public record QuestionResponse(
        UUID uuid,
        String value,
        Difficulty difficulty
) {
}
