package fr.acajou.quiz.repository;

import fr.acajou.quiz.domain.Category;
import fr.acajou.quiz.domain.Difficulty;
import fr.acajou.quiz.domain.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface IQuizRepository extends JpaRepository<Quiz, Long> {

    boolean existsByNumberOfQuestionsAndCategoryAndDifficulty(Integer numberOfQuestions, Category category, Difficulty difficulty);
    boolean deleteByUuid(UUID uuid);
    Optional<Quiz> findByUuid(UUID uuid);
}
