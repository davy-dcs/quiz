package fr.acajou.quiz.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class QuizConflictException extends RuntimeException {
    public QuizConflictException(String message) {
        super(message);
    }
}
