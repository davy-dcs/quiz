package fr.acajou.quiz.dto;

import fr.acajou.quiz.domain.Users;

import java.io.Serializable;

/**
 * DTO for {@link Users}
 */
public record RegisterRequest(String username, String password) implements Serializable {
}