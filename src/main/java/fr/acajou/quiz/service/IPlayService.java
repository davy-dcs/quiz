package fr.acajou.quiz.service;

import fr.acajou.quiz.dto.play.PlayRequest;
import fr.acajou.quiz.dto.play.PlayResponse;
import fr.acajou.quiz.dto.quiz.QuizPlayResponse;
import fr.acajou.quiz.dto.user.UserUuid;

import java.util.List;
import java.util.UUID;

public interface IPlayService {
    PlayResponse post(PlayRequest playRequest);
    List<PlayResponse> getPlaies(UserUuid user);
    QuizPlayResponse letsPlay(UUID uuid);
}
