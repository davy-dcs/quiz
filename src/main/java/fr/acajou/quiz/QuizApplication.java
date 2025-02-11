package fr.acajou.quiz;

import fr.acajou.quiz.domain.*;
import fr.acajou.quiz.repository.IQuizRepository;
import fr.acajou.quiz.repository.IRoleRepository;
import fr.acajou.quiz.repository.IUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@SpringBootApplication
@RequiredArgsConstructor
public class QuizApplication implements CommandLineRunner {

	private final IRoleRepository roleRepository;
	private final IUserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	private final IQuizRepository quizRepository;

	public static void main(String[] args) {
		SpringApplication.run(QuizApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		initializeData();

	}

	private void initializeData() {
		// Ajout des Roles
		Role role1 = new Role("ROLE_ADMIN");
		Role role2 = new Role("ROLE_USER");
		roleRepository.saveAll(List.of(role1, role2));

		// Ajout des Users
		Users user1 = Users.builder()
				.username("admin")
				.password(passwordEncoder.encode("admin"))
				.role(role1)
				.build();
		Users user2 = Users.builder()
				.username("user")
				.password(passwordEncoder.encode("user"))
				.role(role2)
				.build();
		userRepository.saveAll(List.of(user1, user2));

		//Ajout d'un quiz
		Quiz quiz1 = Quiz.builder()
				.title("title quiz")
				.description("description quiz")
				.category(Category.HISTORY)
				.difficulty(Difficulty.EASY)
				.numberOfQuestions(20)
				.build();
		quizRepository.save(quiz1);
	}
}
