package fr.acajou.quiz.exception;

import fr.acajou.quiz.controller.QuizController;
import fr.acajou.quiz.controller.SessionController;
import fr.acajou.quiz.domain.Category;
import fr.acajou.quiz.domain.Difficulty;
import fr.acajou.quiz.dto.quiz.QuizRequest;
import fr.acajou.quiz.dto.session.SessionRequest;
import org.apache.coyote.BadRequestException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

class HandleControllerExceptionTest {

    @InjectMocks
    private HandleControllerException handleControllerException;
    @Mock
    private QuizController quizController;
    @Mock
    private SessionController sessionController;

    private UUID uuid;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        uuid = UUID.randomUUID();
    }

    @Test
    void handleBadRequest() {
        //given
        QuizRequest quizRequest = new QuizRequest("","a",20, Category.HISTORY, Difficulty.EASY);
        given(quizController.post(quizRequest)).willAnswer( invocation -> { throw new BadRequestException("Mauvaise requete bro"); });

        //then
        assertThrows(BadRequestException.class, () -> quizController.post(quizRequest));
    }

    @Test
    void handleQuizConflict() {
        //given
        QuizRequest quizRequest = new QuizRequest("","a",20, Category.HISTORY, Difficulty.EASY);
        given(quizController.post(quizRequest)).willAnswer( invocation -> { throw new QuizConflictException("Quiz existe déjà"); });

        //then
        assertThrows(QuizConflictException.class, () -> quizController.post(quizRequest));
    }

    @Test
    void handleSessionConflict() {
        //given
        SessionRequest sessionRequest = new SessionRequest(null,UUID.randomUUID(),UUID.randomUUID());
        given(sessionController.post(sessionRequest)).willAnswer( invocation -> { throw new SessionConflictException("Session similaire existe déjà"); });

        //then
        assertThrows(SessionConflictException.class, () -> sessionController.post(sessionRequest));
    }

    @Test
    void handleNotFound() {
        //given
        QuizRequest quizRequest = new QuizRequest("","a",20, Category.HISTORY, Difficulty.EASY);
        given(quizController.delete(uuid)).willAnswer( invocation -> { throw new QuizNotFoundException("Quiz non trouvé"); });

        //then
        assertThrows(QuizNotFoundException.class, () -> quizController.delete(uuid));
    }

    @Test
    void handleException() {
        //given
        QuizRequest quizRequest = new QuizRequest("","a",20, Category.HISTORY, Difficulty.EASY);
        given(quizController.post(quizRequest)).willAnswer( invocation -> { throw new Exception("exception retournée"); });

        //then
        assertThrows(Exception.class, () -> quizController.post(quizRequest));
    }
}