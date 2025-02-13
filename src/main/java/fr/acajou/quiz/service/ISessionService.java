package fr.acajou.quiz.service;

import fr.acajou.quiz.domain.Session;
import fr.acajou.quiz.dto.session.SessionRequest;
import fr.acajou.quiz.dto.session.SessionResponse;
import fr.acajou.quiz.dto.user.UserUuid;

import java.util.List;
import java.util.UUID;

public interface ISessionService {
    SessionResponse post(SessionRequest sessionRequest);
    Session getSession(UUID uuid);
    List<SessionResponse> getSessions(UserUuid user);
}
