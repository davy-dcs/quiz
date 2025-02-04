package fr.acajou.quiz.repository;

import fr.acajou.quiz.domain.QuizQuestion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IQuizQuestionRepository extends JpaRepository<QuizQuestion, Long> {
}
