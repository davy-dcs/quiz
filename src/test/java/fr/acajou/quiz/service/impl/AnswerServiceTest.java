package fr.acajou.quiz.service.impl;

import fr.acajou.quiz.domain.Answer;
import fr.acajou.quiz.domain.Difficulty;
import fr.acajou.quiz.domain.Question;
import fr.acajou.quiz.dto.AnswerDTO;
import fr.acajou.quiz.dto.QuestionDTO;
import fr.acajou.quiz.dto.mapper.AnswerMapper;
import fr.acajou.quiz.dto.mapper.QuestionMapper;
import fr.acajou.quiz.repository.IAnswerRepository;
import fr.acajou.quiz.repository.IQuestionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;
import java.util.Random;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class AnswerServiceTest {

    @Mock
    private IAnswerRepository answerRepository;

    @Mock
    AnswerMapper answerMapper;

    @InjectMocks
    private AnswerService answerService;

    private Long id;
    private UUID uuid;
    private Answer answer;
    private Answer answerUpdate;
    private AnswerDTO answerDTO;
    private AnswerDTO answerDTOUpdate;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        id = new Random().nextLong(100000);
        uuid = UUID.randomUUID();
        answer = new Answer(id,uuid,"Test Answer");
        answerUpdate = new Answer(id,uuid,"Test Answer Update");
        answerDTO = new AnswerDTO(uuid,"Test Answer");
        answerDTOUpdate = new AnswerDTO(uuid,"Test Answer Update");
    }

    @Test
    void createAnswer() {
        when(answerMapper.entityToDto(answer)).thenReturn(answerDTO);
        when(answerRepository.save(any(Answer.class))).thenReturn(answer);

        AnswerDTO answerResult = answerService.createAnswer(answerDTO);
        assertEquals(answer.getValue(), answerResult.value());
        verify(answerRepository, times(1)).save(any(Answer.class));
    }

    @Test
    void updateAnswer() {
        when(answerRepository.findByUuid(uuid)).thenReturn(Optional.ofNullable(answer));
        when(answerMapper.dtoToEntity(answerDTOUpdate,id)).thenReturn(answerUpdate);
        when(answerMapper.entityToDto(answerUpdate)).thenReturn(answerDTOUpdate);
        when(answerRepository.save(any(Answer.class))).thenReturn(answerUpdate);

        AnswerDTO answerResult = answerService.updateAnswer(answerDTOUpdate);

        assertEquals(answerUpdate.getValue(), answerResult.value());
        verify(answerRepository, times(1)).save(any(Answer.class));
    }

    @Test
    void getAnswerbyUUID() {
        when(answerRepository.findByUuid(uuid)).thenReturn(Optional.ofNullable(answer));
        when(answerMapper.entityToDto(answer)).thenReturn(answerDTO);

        AnswerDTO answerResult = answerService.getAnswerbyUUID(uuid);
        assertNotNull(answerResult);
    }

    @Test
    void deleteAnswerbyUUID() {
        when(answerRepository.findByUuid(uuid)).thenReturn(Optional.ofNullable(answer));
        assertAll(() -> answerService.deleteAnswerbyUUID(uuid));
    }
}