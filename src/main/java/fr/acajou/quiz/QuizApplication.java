package fr.acajou.quiz;

import fr.acajou.quiz.domain.Role;
import fr.acajou.quiz.domain.Users;
import fr.acajou.quiz.repository.IRoleRepository;
import fr.acajou.quiz.repository.IUserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@SpringBootApplication
@Slf4j
@RequiredArgsConstructor
public class QuizApplication implements CommandLineRunner {

	private final IRoleRepository roleRepository;
	private final IUserRepository userRepository;
	private final PasswordEncoder passwordEncoder;

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
	}

}
