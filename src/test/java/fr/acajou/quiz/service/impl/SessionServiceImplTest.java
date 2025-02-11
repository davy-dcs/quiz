package fr.acajou.quiz.service.impl;

import fr.acajou.quiz.domain.Quiz;
import fr.acajou.quiz.domain.Role;
import fr.acajou.quiz.domain.Users;
import fr.acajou.quiz.dto.session.SessionRequest;
import fr.acajou.quiz.dto.session.SessionResponse;
import fr.acajou.quiz.exception.QuizNotFoundException;
import fr.acajou.quiz.exception.SessionConflictException;
import fr.acajou.quiz.exception.UserNotFoundException;
import fr.acajou.quiz.repository.ISessionRepository;
import fr.acajou.quiz.service.IQuizService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.UUID;

import static fr.acajou.quiz.domain.Category.HISTORY;
import static fr.acajou.quiz.domain.Difficulty.EASY;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
@Slf4j
@Disabled
class SessionServiceImplTest {
    @InjectMocks
    private SessionServiceImpl sessionService;
    @Mock
    private ISessionRepository sessionRepository;
    @Mock
    private UserDetailsServiceImpl userService;
    @Mock
    private IQuizService quizService;

    private Users user;
    private Quiz quiz;
    private SessionRequest sessionRequest;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        user = new Users(5L, UUID.randomUUID(), "username", "password", new Role("ADMIN"));
        quiz = Quiz.builder()
                .title("titre")
                .description("description")
                .category(HISTORY)
                .difficulty(EASY).build();
        sessionRequest = new SessionRequest(null, UUID.randomUUID(), UUID.randomUUID());
    }


    @Test
    void shouldReturnSessionConflictException() {
        //given
        when(userService.getUser(user.getUuid())).thenReturn(user);
        when(quizService.getQuiz(quiz.getUuid())).thenReturn(quiz);
        given(sessionRepository.existsByTimerAndQuiz_UuidAndUser_Uuid(sessionRequest.timer(), sessionRequest.quiz(), sessionRequest.user())).willReturn(true);

        //then
        assertThrows(SessionConflictException.class, () -> sessionService.post(sessionRequest));
    }

    @Test
    void shouldReturnQuizNotFoundException() {
        //given
        given(sessionService.post(sessionRequest)).willThrow(QuizNotFoundException.class);
        //then
        assertThrows(QuizNotFoundException.class, () -> sessionService.post(sessionRequest));
    }

    @Test
    void shouldReturnUserNotFoundException() {
        //given
        given(sessionService.post(sessionRequest)).willThrow(UserNotFoundException.class);
        //then
        assertThrows(UserNotFoundException.class, () -> sessionService.post(sessionRequest));
    }

    @Test
    void shouldReturnSessionResponse() {
        //given
        given(userService.getUser(user.getUuid())).willReturn(user);
        given(quizService.getQuiz(quiz.getUuid())).willReturn(quiz);
        given(sessionRepository.existsByTimerAndQuiz_UuidAndUser_Uuid(null, quiz.getUuid(), user.getUuid())).willReturn(false);

        //when
        final SessionResponse actual = sessionService.post(sessionRequest);

        //then
        assertNotNull(actual);
    }
}