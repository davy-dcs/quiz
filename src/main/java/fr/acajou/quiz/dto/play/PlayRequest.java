package fr.acajou.quiz.dto.play;

import fr.acajou.quiz.dto.session.SessionUuid;
import fr.acajou.quiz.dto.user.UserUuid;

public record PlayRequest(
        UserUuid userUuid,
        SessionUuid sessionUuid
) {
}
