package fr.acajou.quiz.controller;

import fr.acajou.quiz.dto.play.PlayRequest;
import fr.acajou.quiz.dto.play.PlayResponse;
import fr.acajou.quiz.dto.quiz.QuizPlayResponse;
import fr.acajou.quiz.service.IPlayService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/plaies")
@RequiredArgsConstructor
@Secured("ROLE_USER")
public class PlayController {/*
    private final IPlayService playService;
    @PostMapping
    public ResponseEntity<PlayResponse> post(@RequestBody PlayRequest playRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(playService.post(playRequest));
    }

    @GetMapping("/{play}")
    public ResponseEntity<QuizPlayResponse> play(@RequestParam UUID play) {
        return ResponseEntity.status(HttpStatus.OK).body(playService.letsPlay(play));
    }*/
}
