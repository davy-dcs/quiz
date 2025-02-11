package fr.acajou.quiz.service.impl;

import fr.acajou.quiz.domain.Users;
import fr.acajou.quiz.exception.UserNotFoundException;
import fr.acajou.quiz.repository.IUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final IUserRepository userRepository;

    public Users getUser(UUID uuid) {
        if (userRepository.findByUuid(uuid).isPresent()) {
            return userRepository.findByUuid(uuid).get();
        } else {
            throw new UserNotFoundException("L'utilisateur n'existe pas.");
        }
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username).orElse(null);
    }
}
