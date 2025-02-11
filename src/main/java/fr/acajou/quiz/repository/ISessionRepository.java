package fr.acajou.quiz.repository;

import fr.acajou.quiz.domain.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ISessionRepository extends JpaRepository<Session, Long> {
    boolean existsByTimerAndQuiz_UuidAndUser_Uuid(Integer timer, UUID quizUuid, UUID userUuid);
}
