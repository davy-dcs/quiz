package fr.acajou.quiz.dto.session;

import jakarta.validation.constraints.NotBlank;

import java.util.UUID;

public record SessionRequest(
        Integer timer,
        @NotBlank
        UUID quiz,
        @NotBlank
        UUID user
) {
}
