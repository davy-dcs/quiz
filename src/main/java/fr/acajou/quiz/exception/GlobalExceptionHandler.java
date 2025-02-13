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

    @ExceptionHandler(QuestionListNotFoundException.class)
    public ResponseEntity<String> QuestionListNotFoundException(QuestionNotFoundException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND.value()).body(exception.getMessage());
    }

    @ExceptionHandler(AnswerNotFoundException.class)
    public ResponseEntity<String> AnswerNotFoundException(AnswerNotFoundException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND.value()).body(exception.getMessage());
    }

    @ExceptionHandler(QuestionAnswerNotFoundException.class)
    public ResponseEntity<String> QuestionAnswerNotFoundException(QuestionAnswerNotFoundException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND.value()).body(exception.getMessage());
    }
}
