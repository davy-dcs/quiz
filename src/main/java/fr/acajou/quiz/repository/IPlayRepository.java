package fr.acajou.quiz.repository;

import fr.acajou.quiz.domain.Play;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPlayRepository extends JpaRepository<Play, Long> {
}
