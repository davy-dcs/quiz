package fr.acajou.quiz.service.impl;

import fr.acajou.quiz.domain.Quiz;
import fr.acajou.quiz.domain.Session;
import fr.acajou.quiz.domain.Users;
import fr.acajou.quiz.dto.session.ISessionMapper;
import fr.acajou.quiz.dto.session.SessionRequest;
import fr.acajou.quiz.dto.session.SessionResponse;
import fr.acajou.quiz.exception.SessionConflictException;
import fr.acajou.quiz.repository.ISessionRepository;
import fr.acajou.quiz.repository.IUserRepository;
import fr.acajou.quiz.service.IQuizService;
import fr.acajou.quiz.service.ISessionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SessionServiceImpl implements ISessionService {
    private final ISessionRepository sessionRepository;
    private final UserDetailsServiceImpl userService;
    private final IQuizService quizService;

    @Override
    public SessionResponse post(SessionRequest sessionRequest) {
        Users user = userService.getUser(sessionRequest.user());
        Quiz quiz = quizService.getQuiz(sessionRequest.quiz());

        boolean sessionExists = sessionRepository.existsByTimerAndQuiz_UuidAndUser_Uuid(sessionRequest.timer(), quiz.getUuid(), user.getUuid());
        if (sessionExists) {
            throw new SessionConflictException("Une session similaire existe déjà.");
        } else {
            Session session = Session.builder().timer(sessionRequest.timer()).quiz(quiz).user(user).build();
            return ISessionMapper.INSTANCE.sessionToSessionResponse(sessionRepository.save(session));
        }
    }
}
