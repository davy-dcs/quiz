package fr.acajou.quiz.repository;

import fr.acajou.quiz.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IRoleRepository extends JpaRepository<Role, String> {

}
