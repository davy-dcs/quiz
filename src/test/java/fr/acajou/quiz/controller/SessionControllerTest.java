package fr.acajou.quiz.controller;

import fr.acajou.quiz.dto.session.SessionRequest;
import fr.acajou.quiz.dto.session.SessionResponse;
import fr.acajou.quiz.exception.SessionConflictException;
import fr.acajou.quiz.service.ISessionService;
import org.apache.coyote.BadRequestException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.time.Instant;
import java.util.Date;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;

class SessionControllerTest {
    @InjectMocks
    private SessionController sessionController;
    @Mock
    private ISessionService sessionService;

    private SessionRequest sessionRequest;
    private SessionResponse sessionResponse;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        sessionRequest = new SessionRequest(null, UUID.randomUUID(), UUID.randomUUID());
        sessionResponse = new SessionResponse(null, UUID.randomUUID(), Date.from(Instant.now()), UUID.randomUUID(), UUID.randomUUID());
    }

    @Test
    void shouldReturnSessionResponse() {
        //given
        when(sessionService.post(sessionRequest)).thenReturn(sessionResponse);

        //when
        final ResponseEntity<SessionResponse> actual = sessionController.post(sessionRequest);

        //then
        assertEquals(201, actual.getStatusCode().value());
        assertNotNull(actual.getBody());
        assertEquals(sessionResponse.uuid(), actual.getBody().uuid());
        assertEquals(sessionResponse.date(), actual.getBody().date());
        assertEquals(sessionResponse.quiz(), actual.getBody().quiz());
        assertEquals(sessionResponse.user(), actual.getBody().user());
    }

    @Test
    void shouldReturnSessionConflict() {
        //given
        given(sessionService.post(sessionRequest)).willAnswer( invocation -> { throw new SessionConflictException("Une session similaire existe déjà."); });

        //then
        assertThrows(SessionConflictException.class, () -> sessionController.post(sessionRequest));
    }

    @Test
    void shouldReturnBadRequest() {
        //given
        given(sessionService.post(sessionRequest)).willAnswer(invocation -> {throw  new BadRequestException("bad request");});

        //then
        assertThrows(BadRequestException.class, () -> sessionController.post(sessionRequest));
    }
}