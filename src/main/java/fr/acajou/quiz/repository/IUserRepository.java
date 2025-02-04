package fr.acajou.quiz.repository;


import fr.acajou.quiz.domain.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IUserRepository extends JpaRepository<Users, Long> {

    Optional<Users> findByUsername(String username);
}
