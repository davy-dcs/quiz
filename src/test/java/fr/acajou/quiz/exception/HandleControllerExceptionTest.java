package fr.acajou.quiz.exception;

import fr.acajou.quiz.controller.QuizController;
import fr.acajou.quiz.domain.Category;
import fr.acajou.quiz.domain.Difficulty;
import fr.acajou.quiz.domain.Quiz;
import fr.acajou.quiz.dto.QuizRequest;
import fr.acajou.quiz.dto.QuizResponse;
import org.apache.coyote.BadRequestException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.UUID;

import static fr.acajou.quiz.domain.Category.*;
import static fr.acajou.quiz.domain.Difficulty.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;

class HandleControllerExceptionTest {

    @InjectMocks
    private HandleControllerException handleControllerException;
    @Mock
    private QuizController quizController;

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
    void handleConflict() {
        //given
        QuizRequest quizRequest = new QuizRequest("","a",20, Category.HISTORY, Difficulty.EASY);
        given(quizController.post(quizRequest)).willAnswer( invocation -> { throw new QuizConflictException("Quiz existe déjà"); });

        //then
        assertThrows(QuizConflictException.class, () -> quizController.post(quizRequest));
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