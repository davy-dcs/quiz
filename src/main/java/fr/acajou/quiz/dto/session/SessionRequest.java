package fr.acajou.quiz.dto.session;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record SessionRequest(
        Integer timer,
        @NotNull
        UUID quiz,
        @NotNull
        UUID user
) {
}
