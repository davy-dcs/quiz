package fr.acajou.quiz.dto.session;

import jakarta.validation.constraints.NotBlank;

import java.util.Date;
import java.util.UUID;

public record SessionResponse(
        Integer timer,
        UUID uuid,
        Date date,
        @NotBlank
        UUID quiz,
        @NotBlank
        UUID user) {
}
