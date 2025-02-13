package fr.acajou.quiz.controller;

import fr.acajou.quiz.dto.quiz.QuizRequest;
import fr.acajou.quiz.dto.quiz.QuizResponse;
import fr.acajou.quiz.service.IQuizService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/quizzes")
@RequiredArgsConstructor
@Secured("ROLE_ADMIN")
public class QuizController {
    private final IQuizService quizService;

    @GetMapping
    @Secured("ROLE_USER")
    public ResponseEntity<List<QuizResponse>> getAll() {
        return ResponseEntity.status(HttpStatus.OK).body(quizService.getAll());
    }

    @PostMapping
    public ResponseEntity<QuizResponse> post(@RequestBody @Valid QuizRequest quizRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(quizService.post(quizRequest));
    }

    @DeleteMapping("/{quiz}")
    public ResponseEntity<String> delete(@PathVariable UUID quiz) {
        return ResponseEntity.status(HttpStatus.OK).body(quizService.delete(quiz));
    }
}
