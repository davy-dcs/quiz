package fr.acajou.quiz.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class SessionConflictException extends RuntimeException {
    public SessionConflictException(String message) {
        super(message);
    }
}
