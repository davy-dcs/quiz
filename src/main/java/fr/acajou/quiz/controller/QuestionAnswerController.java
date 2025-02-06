package fr.acajou.quiz.controller;

import fr.acajou.quiz.dto.QuestionAnswerDTO;
import fr.acajou.quiz.dto.QuestionDTO;
import fr.acajou.quiz.service.impl.QuestionAnswerService;
import fr.acajou.quiz.service.impl.QuestionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/questionAnswer")
@RequiredArgsConstructor
public class QuestionAnswerController {

    private final QuestionAnswerService questionAnswerService;

    //Associe une question à une réponse
    @PostMapping
    public ResponseEntity<QuestionAnswerDTO> createQuestionAnswer(@Valid @RequestBody QuestionAnswerDTO questionAnswerDTO) {
        return ResponseEntity.ok(questionAnswerService.createQuestionAnswer(questionAnswerDTO));
    }

    @PutMapping
    public ResponseEntity<QuestionAnswerDTO> updateQuestionAnswer(@Valid @RequestBody QuestionAnswerDTO questionAnswerDTO) {
        return ResponseEntity.ok(questionAnswerService.updateQuestionAnswer(questionAnswerDTO));
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<QuestionAnswerDTO> getQuestionAnswer(@PathVariable UUID uuid){
        QuestionAnswerDTO questionAnswerDTO = questionAnswerService.getQuestionbyUUID(uuid);
        return ResponseEntity.ok(questionAnswerDTO);
    }

    @DeleteMapping("/{uuid}")
    public ResponseEntity<Void> deleteQuestionAnswer(@PathVariable UUID uuid){
        questionAnswerService.deleteQuestionAnswerbyUUID(uuid);
        return ResponseEntity.noContent().build();
    }
}
