package fr.acajou.quiz.repository;

import fr.acajou.quiz.domain.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ISessionRepository extends JpaRepository<Session, Long> {
}
