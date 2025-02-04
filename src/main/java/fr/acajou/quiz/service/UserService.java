package fr.acajou.quiz.service;


import fr.acajou.quiz.domain.Users;
import fr.acajou.quiz.dto.AuthenticationRequest;
import fr.acajou.quiz.dto.AuthenticationResponse;
import fr.acajou.quiz.dto.RegisterRequest;
import fr.acajou.quiz.repository.IUserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {

    private final IUserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public void register(RegisterRequest request) {
        Users users = Users.builder()
                .username(request.username())
                .password(passwordEncoder.encode(request.password()))
                .build();
        userRepository.save(users);
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        try {
            final UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                    request.username(),
                    request.password()
            );
            Users users = userRepository.findByUsername(request.username()).orElseThrow();
            authenticationManager.authenticate(authenticationToken);
            final String token = jwtService.generateToken(users);
            return new AuthenticationResponse(token);
        }catch (AuthenticationException e) {
            throw new RuntimeException("Invalid username/password supplied");
        }
    }
}
