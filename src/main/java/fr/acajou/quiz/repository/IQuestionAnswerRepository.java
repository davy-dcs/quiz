package fr.acajou.quiz.repository;

import fr.acajou.quiz.domain.Question;
import fr.acajou.quiz.domain.QuestionAnswer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface IQuestionAnswerRepository extends JpaRepository<QuestionAnswer, Long> {

    Optional<QuestionAnswer> findByUuid(UUID uuid);



}
