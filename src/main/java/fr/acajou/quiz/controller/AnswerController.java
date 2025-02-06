package fr.acajou.quiz.controller;

import fr.acajou.quiz.dto.AnswerDTO;
import fr.acajou.quiz.service.impl.AnswerService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/answer")
@AllArgsConstructor
public class AnswerController {

    private final AnswerService answerService;

    @PostMapping
    public ResponseEntity<AnswerDTO> createAnswer(@Valid @RequestBody AnswerDTO answerDTO) {
        return ResponseEntity.ok(answerService.createAnswer(answerDTO));
    }

    @PutMapping
    public ResponseEntity<AnswerDTO> updateAnswer(@Valid @RequestBody AnswerDTO answerDTO) {
        return ResponseEntity.ok(answerService.updateAnswer(answerDTO));
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<AnswerDTO> getAnswer(@PathVariable UUID uuid){
        AnswerDTO answerDTO = answerService.getAnswerbyUUID(uuid);
        return ResponseEntity.ok(answerDTO);
    }

    @DeleteMapping("/{uuid}")
    public ResponseEntity<Void> deleteAnswer(@PathVariable UUID uuid){
        answerService.deleteAnswerbyUUID(uuid);
        return ResponseEntity.noContent().build();
    }
}
