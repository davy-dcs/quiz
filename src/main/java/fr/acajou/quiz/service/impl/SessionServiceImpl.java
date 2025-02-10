package fr.acajou.quiz.service.impl;

import fr.acajou.quiz.dto.session.ISessionMapper;
import fr.acajou.quiz.dto.session.SessionRequest;
import fr.acajou.quiz.dto.session.SessionResponse;
import fr.acajou.quiz.exception.SessionConflictException;
import fr.acajou.quiz.repository.ISessionRepository;
import fr.acajou.quiz.service.ISessionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SessionServiceImpl implements ISessionService {
    private final ISessionRepository sessionRepository;

    @Override
    public SessionResponse post(SessionRequest sessionRequest) {
        boolean sessionExists = sessionRepository.existsByTimerAndQuiz_UuidAndUsers_Uuid(sessionRequest.timer(), sessionRequest.quiz(), sessionRequest.user());
        if (sessionExists) {
            throw new SessionConflictException("Une session similaire existe déjà.");
        }

        return ISessionMapper.INSTANCE.sessionToSessionResponse(sessionRepository.save(ISessionMapper.INSTANCE.sessionRequestToSession(sessionRequest)));
    }
}
