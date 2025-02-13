package fr.acajou.quiz.controller;

import fr.acajou.quiz.dto.user.AuthenticationRequest;
import fr.acajou.quiz.dto.user.AuthenticationResponse;
import fr.acajou.quiz.dto.user.RegisterRequest;
import fr.acajou.quiz.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@Slf4j
public class AuthController {

    private final UserService userService;


    /**
     * Registers a new user based on the provided registration request.
     *
     * @param request The registration request containing user details
     * @return ResponseEntity containing an authentication response
     * @author Fethi Benseddik
     */
    @PostMapping("/register")
    public ResponseEntity<Void> register(
            @RequestBody RegisterRequest request
    ) {
        userService.register(request);
        log.info("REST request to register user {}", request.username());
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    /**
     * Authenticates a user based on the provided authentication request.
     *
     * @param request The authentication request containing user credentials
     * @return ResponseEntity containing an authentication response
     * @author Fethi Benseddik
     */
    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody AuthenticationRequest request
    ) {
        log.info("REST request to authenticate user {}", request.username());
        return ResponseEntity.ok(userService.authenticate(request));
    }
}
