package fr.acajou.quiz.controller;

import fr.acajou.quiz.dto.session.SessionRequest;
import fr.acajou.quiz.dto.session.SessionResponse;
import fr.acajou.quiz.dto.user.UserUuid;
import fr.acajou.quiz.service.ISessionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sessions")
@RequiredArgsConstructor
@Secured("ROLE_USER")
public class SessionController {
    private final ISessionService sessionService;

    @PostMapping
    public ResponseEntity<SessionResponse> post(@RequestBody @Valid SessionRequest sessionRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(sessionService.post(sessionRequest));
    }

    @GetMapping
    public ResponseEntity<List<SessionResponse>> getSessions(@RequestBody UserUuid user) {
        return ResponseEntity.status(HttpStatus.OK).body(sessionService.getSessions(user));
    }
}
