package fr.acajou.quiz.repository;

import fr.acajou.quiz.domain.Play;
import fr.acajou.quiz.domain.Session;
import fr.acajou.quiz.domain.Users;
import fr.acajou.quiz.dto.quiz.QuizPlayResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface IPlayRepository extends JpaRepository<Play, Long> {
    List<Play> findByUsers_Uuid(UUID uuid);

}
