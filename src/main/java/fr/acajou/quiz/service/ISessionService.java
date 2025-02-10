package fr.acajou.quiz.service;

import fr.acajou.quiz.dto.session.SessionRequest;
import fr.acajou.quiz.dto.session.SessionResponse;

public interface ISessionService {
    SessionResponse post(SessionRequest sessionRequest);
}
