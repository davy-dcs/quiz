package fr.acajou.quiz.repository;

import fr.acajou.quiz.domain.Question;
import fr.acajou.quiz.dto.QuestionDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface IQuestionRepository extends JpaRepository<Question, Long> {

    Optional<Question> findByUuid(UUID uuid);

}
