package fr.acajou.quiz.dto.answer;

import java.util.UUID;

public record AnswerResponse(
        UUID uuid,
        String value,
        Boolean correct
) {
}
