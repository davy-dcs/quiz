package fr.acajou.quiz.service.impl;

import fr.acajou.quiz.domain.*;
import fr.acajou.quiz.dto.QuestionDTO;
import fr.acajou.quiz.dto.answer.AnswerResponse;
import fr.acajou.quiz.dto.play.IPlayMapper;
import fr.acajou.quiz.dto.play.PlayRequest;
import fr.acajou.quiz.dto.play.PlayResponse;
import fr.acajou.quiz.dto.question.IQuestionMapper;
import fr.acajou.quiz.dto.question.QuestionResponse;
import fr.acajou.quiz.dto.questionAnswer.QuestionAnswersResponse;
import fr.acajou.quiz.dto.quiz.QuizPlayResponse;
import fr.acajou.quiz.dto.user.UserUuid;
import fr.acajou.quiz.exception.PlayNotFoundException;
import fr.acajou.quiz.repository.IPlayRepository;
import fr.acajou.quiz.repository.IQuestionAnswerRepository;
import fr.acajou.quiz.service.IPlayService;
import fr.acajou.quiz.service.ISessionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class PlayServiceImpl implements IPlayService {
    private final IPlayRepository playRepository;
    private final UserDetailsServiceImpl userService;
    private final ISessionService sessionService;
    private final QuestionService questionService;

    @Override
    public PlayResponse post(PlayRequest playRequest) {
        Users user = userService.getUser(playRequest.userUuid().uuid());
        Session session = sessionService.getSession(playRequest.sessionUuid().uuid());

        return IPlayMapper.INSTANCE.playToPlayResponse(playRepository.save(IPlayMapper.INSTANCE.playRequestToPlay(playRequest)));
    }

    @Override
    public List<PlayResponse> getPlaies(UserUuid user) {
        return IPlayMapper.INSTANCE.plaiesToPlaiesResponses(playRepository.findByUsers_Uuid(user.uuid()));
    }

    @Override
    public QuizPlayResponse letsPlay(UUID uuid) {
        Play play = playRepository.findByUuid(uuid).orElseThrow(() -> new PlayNotFoundException("Play not found"));

        Set<UUID> questions = new HashSet<>();
        for (QuestionAnswer questionAnswer : play.getSession().getQuiz().getQuestions()) {
            questions.add(questionAnswer.getQuestion().getUuid());
        }

        List<QuestionAnswersResponse> questionsAnswersResponse = new ArrayList<>();
        for (UUID question_uuid : questions) {
            Question question = questionService.getbyUUID(question_uuid);

            List<AnswerResponse> answers = new ArrayList<>();
            for (QuestionAnswer questionAnswer : question.getAnswers()) {
                answers.add(new AnswerResponse(
                        questionAnswer.getAnswer().getUuid(),
                        questionAnswer.getAnswer().getValue(),
                        questionAnswer.isCorrect()
                ));
            }

            questionsAnswersResponse.add(
                    new QuestionAnswersResponse(
                            question.getUuid(),
                            new QuestionResponse(question.getUuid(), question.getValue(), question.getDifficulty()),
                            answers
                    )
            );
        }

        return new QuizPlayResponse(
                play.getSession().getQuiz().getTitle(),
                play.getSession().getQuiz().getDescription(),
                play.getSession().getQuiz().getCategory(),
                play.getSession().getQuiz().getQuestions()
        );
    }

    @Override
    public Integer addScore(UUID uuid) {
        Play play = playRepository.findByUuid(uuid).orElseThrow(() -> new PlayNotFoundException("Play not found"));

        play.setScore(play.getScore()+1);
        playRepository.save(play);

        return play.getScore();
    }
}
