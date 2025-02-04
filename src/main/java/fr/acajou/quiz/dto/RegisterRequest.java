package fr.acajou.quiz.dto;

import java.io.Serializable;

/**
 * DTO for {@link fr.acajou.quiz.domain.User}
 */
public record RegisterRequest(String username, String password) implements Serializable {
}