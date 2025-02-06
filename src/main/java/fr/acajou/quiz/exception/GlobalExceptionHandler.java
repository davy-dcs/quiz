package fr.acajou.quiz.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(QuestionNotFoundException.class)
    public ResponseEntity<String> QuestionNotFoundException(QuestionNotFoundException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND.value()).body(exception.getMessage());
    }

    @ExceptionHandler(AnswerNotFoundException.class)
    public ResponseEntity<String> QuestionNotFoundException(AnswerNotFoundException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND.value()).body(exception.getMessage());
    }
}
