package fr.acajou.quiz.repository;

import fr.acajou.quiz.domain.QuestionAnswer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IQuestionAnswerRepository extends JpaRepository<QuestionAnswer, Long> {
}
