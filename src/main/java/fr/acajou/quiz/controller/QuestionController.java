package fr.acajou.quiz.controller;

import fr.acajou.quiz.domain.Category;
import fr.acajou.quiz.domain.Difficulty;
import fr.acajou.quiz.dto.QuestionDTO;
import fr.acajou.quiz.service.impl.QuestionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/question")
@RequiredArgsConstructor
public class QuestionController {

    private final QuestionService questionService;

    @PostMapping
    public ResponseEntity<QuestionDTO> createQuestion(@Valid @RequestBody QuestionDTO questionDTO) {
        return ResponseEntity.ok(questionService.createQuestion(questionDTO));
    }

    @PutMapping
    public ResponseEntity<QuestionDTO> updateQuestion(@Valid @RequestBody QuestionDTO questionDTO) {
        return ResponseEntity.ok(questionService.updateQuestion(questionDTO));
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<QuestionDTO> getQuestion(@PathVariable UUID uuid){
        QuestionDTO questionDTO = questionService.getQuestionbyUUID(uuid);
        return ResponseEntity.ok(questionDTO);
    }

    @DeleteMapping("/{uuid}")
    public ResponseEntity<Void> deleteQuestion(@PathVariable UUID uuid){
        questionService.deleteQuestionbyUUID(uuid);
        return ResponseEntity.noContent().build();
    }
}
