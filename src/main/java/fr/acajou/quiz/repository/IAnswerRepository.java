package fr.acajou.quiz.repository;

import fr.acajou.quiz.domain.Answer;
import fr.acajou.quiz.domain.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface IAnswerRepository extends JpaRepository<Answer, Long> {

    Optional<Answer> findByUuid(UUID uuid);

}
