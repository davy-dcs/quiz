package fr.acajou.quiz.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class PlayNotFoundException extends RuntimeException {
    public PlayNotFoundException(String message) {
        super(message);
    }
}
