package fr.acajou.quiz.service.impl;

import fr.acajou.quiz.dto.quiz.QuizRequest;
import fr.acajou.quiz.exception.QuizConflictException;
import fr.acajou.quiz.exception.QuizNotFoundException;
import fr.acajou.quiz.repository.IQuizRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.UUID;

import static fr.acajou.quiz.domain.Category.*;
import static fr.acajou.quiz.domain.Difficulty.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class QuizServiceImplTest {

    @InjectMocks
    private QuizServiceImpl quizService;
    @Mock
    private IQuizRepository quizRepository;

    private QuizRequest quizRequest;
    private UUID uuid;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        quizRequest = new QuizRequest("titre request", "description request", 20, HISTORY, EASY);
        uuid = UUID.randomUUID();
    }

    @Test
    void shouldReturnQuizConflictException() {
        //given
        when(quizRepository.existsByNumberOfQuestionsAndCategoryAndDifficulty(quizRequest.numberOfQuestion(), quizRequest.category(), quizRequest.difficulty())).thenReturn(true);
        //then
        assertThrows(QuizConflictException.class, () -> quizService.post(quizRequest));
    }

    @Test
    void shouldReturnString() {
        //given
        when(quizRepository.deleteByUuid(uuid)).thenReturn(true);

        //when
        String actual = quizService.delete(uuid);

        //then
        assertEquals("Quiz supprimé", actual);
    }

    @Test
    void shouldReturnQuizNotFoundException() {
        //given
        when(quizRepository.deleteByUuid(uuid)).thenReturn(false);
        //then
        assertThrows(QuizNotFoundException.class, () -> quizService.delete(uuid));
    }

}