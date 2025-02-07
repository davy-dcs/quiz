package fr.acajou.quiz.controller;

import fr.acajou.quiz.domain.Category;
import fr.acajou.quiz.domain.Difficulty;
import fr.acajou.quiz.dto.QuizRequest;
import fr.acajou.quiz.dto.QuizResponse;
import fr.acajou.quiz.exception.QuizConflictException;
import fr.acajou.quiz.exception.QuizNotFoundException;
import fr.acajou.quiz.service.IQuizService;
import org.apache.coyote.BadRequestException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.UUID;

import static fr.acajou.quiz.domain.Category.GEOGRAPHY;
import static fr.acajou.quiz.domain.Category.HISTORY;
import static fr.acajou.quiz.domain.Difficulty.EASY;
import static fr.acajou.quiz.domain.Difficulty.MEDIUM;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;

class QuizControllerTest {

    @InjectMocks
    private QuizController quizController;
    @Mock
    private IQuizService quizService;

    private QuizRequest quizRequest;
    private QuizResponse quizResponse;
    private UUID uuid;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        quizRequest = new QuizRequest("titre request", "description request", 20, HISTORY, EASY);
        quizResponse = new QuizResponse("titre reponse", "description response", 30, GEOGRAPHY, MEDIUM);
        uuid = UUID.randomUUID();
    }

    @Test
    void shouldReturnQuizResponse() {
        //given
        when(quizService.post(quizRequest)).thenReturn(quizResponse);

        //when
        final ResponseEntity<QuizResponse> actual = quizController.post(quizRequest);

        //then
        assertEquals(201, actual.getStatusCode().value());
        assertNotNull(actual.getBody());
        assertEquals(quizResponse.title(), actual.getBody().title());
        assertEquals(quizResponse.description(), actual.getBody().description());
        assertEquals(quizResponse.numberOfQuestions(), actual.getBody().numberOfQuestions());
        assertEquals(quizResponse.category(), actual.getBody().category());
        assertEquals(quizResponse.difficulty(), actual.getBody().difficulty());
    }

    @Test
    void shouldReturnQuizConflict() {
        //given
        given(quizService.post(quizRequest)).willAnswer( invocation -> { throw new QuizConflictException("Un quiz similaire existe déjà."); });

        //then
        assertThrows(QuizConflictException.class, () -> quizController.post(quizRequest));
    }

    @Test
    void shouldReturnBadRequest() {
        //given
        given(quizService.post(quizRequest)).willAnswer(invocation -> {throw  new BadRequestException("bad request");});

        //then
        assertThrows(BadRequestException.class, () -> quizController.post(quizRequest));
    }

    @Test
    void shouldReturnString() {
        //given
        when(quizService.delete(uuid)).thenReturn("Quiz supprimé");

        //when
        final ResponseEntity<String> actual = quizController.delete(uuid);

        //then
        assertEquals(200, actual.getStatusCode().value());
        assertNotNull(actual.getBody());
        assertEquals("Quiz supprimé", actual.getBody());
    }

    @Test
    void shouldReturnQuizNotFound() {
        //given
        given(quizService.delete(uuid)).willAnswer(invocation -> {throw  new QuizNotFoundException("Quiz non trouvé");});

        //then
        assertThrows(QuizNotFoundException.class, () -> quizController.delete(uuid));
    }
}