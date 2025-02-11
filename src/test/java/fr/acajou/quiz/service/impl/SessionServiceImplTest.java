package fr.acajou.quiz.service.impl;

import fr.acajou.quiz.domain.Quiz;
import fr.acajou.quiz.domain.Role;
import fr.acajou.quiz.domain.Session;
import fr.acajou.quiz.domain.Users;
import fr.acajou.quiz.dto.session.ISessionMapper;
import fr.acajou.quiz.dto.session.SessionRequest;
import fr.acajou.quiz.dto.session.SessionResponse;
import fr.acajou.quiz.exception.QuizNotFoundException;
import fr.acajou.quiz.exception.SessionConflictException;
import fr.acajou.quiz.exception.UserNotFoundException;
import fr.acajou.quiz.repository.IQuizRepository;
import fr.acajou.quiz.repository.ISessionRepository;
import fr.acajou.quiz.repository.IUserRepository;
import fr.acajou.quiz.service.IQuizService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.stubbing.OngoingStubbing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;

import java.util.Optional;
import java.util.UUID;

import static fr.acajou.quiz.domain.Category.HISTORY;
import static fr.acajou.quiz.domain.Difficulty.EASY;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
@Slf4j
@Disabled
class SessionServiceImplTest {
    @Mock
    private UserDetailsServiceImpl userService;

    @Mock
    private QuizServiceImpl quizService;

    @Mock
    private ISessionRepository sessionRepository;

    @Mock
    private ISessionMapper sessionMapper;

    @InjectMocks
    private SessionServiceImpl sessionService;


    private Users mockUser;
    private Quiz mockQuiz;
    private SessionRequest sessionRequest;
    private Session mockSession;
    private SessionResponse sessionResponse;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockUser = Users.builder()
                .username("test")
                .password("test")
                .role(new Role("ROLE_ADMIN"))
                .build();
        mockQuiz = Quiz.builder()
                .title("titre")
                .description("description")
                .category(HISTORY)
                .difficulty(EASY).build();
        sessionRequest = new SessionRequest(null, mockQuiz.getUuid(), mockUser.getUuid());
        mockSession = Session.builder()
                .timer(null)
                .user(mockUser)
                .quiz(mockQuiz)
                .build();
        sessionResponse = new SessionResponse(mockSession.getTimer(), mockSession.getUuid(), mockSession.getDate(), mockSession.getQuiz().getUuid(), mockSession.getUser().getUuid());
    }


    @Test
    void shouldReturnSessionConflictException() {
        //given
        when(userService.getUser(sessionRequest.user())).thenReturn(mockUser);
        when(quizService.getQuiz(sessionRequest.quiz())).thenReturn(mockQuiz);
        when(sessionRepository.existsByTimerAndQuiz_UuidAndUser_Uuid(sessionRequest.timer(), mockQuiz.getUuid(), mockUser.getUuid())).thenReturn(true);

        //then
        assertThrows(SessionConflictException.class, () -> {sessionService.post(sessionRequest);});
    }

    @Test
    void shouldReturnQuizNotFoundException() {
        //given
        when(userService.getUser(sessionRequest.user())).thenReturn(mockUser);
        when(quizService.getQuiz(sessionRequest.quiz())).thenThrow(QuizNotFoundException.class);

        //then
        assertThrows(QuizNotFoundException.class, () -> sessionService.post(sessionRequest));
    }

    @Test
    void shouldReturnUserNotFoundException() {
        //given
        when(userService.getUser(sessionRequest.user())).thenThrow(UserNotFoundException.class);

        //then
        assertThrows(UserNotFoundException.class, () -> sessionService.post(sessionRequest));
    }

    @Test
    void shouldReturnSessionResponse() {
        //given
        when(userService.getUser(sessionRequest.user())).thenReturn(mockUser);
        when(quizService.getQuiz(sessionRequest.quiz())).thenReturn(mockQuiz);
        when(sessionRepository.existsByTimerAndQuiz_UuidAndUser_Uuid(sessionRequest.timer(), mockQuiz.getUuid(), mockUser.getUuid())).thenReturn(false);
        when(sessionRepository.save(any(Session.class))).thenReturn(mockSession);
        when(sessionMapper.sessionToSessionResponse(any(Session.class))).thenReturn(sessionResponse);

        //when
        final SessionResponse actual = sessionService.post(sessionRequest);

        //then
        assertNotNull(actual);
    }
}