package fr.acajou.quiz.service.impl;

import fr.acajou.quiz.domain.Quiz;
import fr.acajou.quiz.domain.Session;
import fr.acajou.quiz.domain.Users;
import fr.acajou.quiz.dto.session.ISessionMapper;
import fr.acajou.quiz.dto.session.SessionRequest;
import fr.acajou.quiz.dto.session.SessionResponse;
import fr.acajou.quiz.dto.user.UserUuid;
import fr.acajou.quiz.exception.SessionConflictException;
import fr.acajou.quiz.exception.SessionNotFoundException;
import fr.acajou.quiz.repository.ISessionRepository;
import fr.acajou.quiz.service.IPlayService;
import fr.acajou.quiz.service.IQuizService;
import fr.acajou.quiz.service.ISessionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

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
            Session sessionSave = sessionRepository.save(session);
            return ISessionMapper.INSTANCE.sessionToSessionResponse(sessionSave);
        }
    }

    @Override
    public List<SessionResponse> getSessions(UserUuid user) {
        return ISessionMapper.INSTANCE.sessionsToSessionsResponses(sessionRepository.findByUser_Uuid(user.uuid()));
    }

    @Override
    public Session getSession(UUID uuid) {
        if (sessionRepository.findByUuid(uuid).isPresent()) {
            return sessionRepository.findByUuid(uuid).get();
        } else {
            throw new SessionNotFoundException("La session n'existe pas.");
        }
    }
}
