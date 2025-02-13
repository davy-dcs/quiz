package fr.acajou.quiz.service.impl;

import fr.acajou.quiz.domain.*;
import fr.acajou.quiz.dto.QuestionAnswerDTO;
import fr.acajou.quiz.dto.QuestionDTO;
import fr.acajou.quiz.dto.mapper.QuestionAnswerMapper;
import fr.acajou.quiz.dto.mapper.QuestionMapper;
import fr.acajou.quiz.repository.IAnswerRepository;
import fr.acajou.quiz.repository.IQuestionAnswerRepository;
import fr.acajou.quiz.repository.IQuestionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.*;

import static fr.acajou.quiz.domain.Category.GENERAL_KNOWLEDGE;
import static fr.acajou.quiz.domain.Category.HISTORY;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class QuestionAnswerServiceTest {
    @Mock
    private IQuestionAnswerRepository questionAnswerRepository;

    @Mock
    private IQuestionRepository questionRepository;

    @Mock
    private IAnswerRepository answerRepository;

    @Mock
    QuestionAnswerMapper questionAnswerMapper;

    @Mock
    private QuestionService questionService;

    @Mock
    private AnswerService answerService;

    @InjectMocks
    private QuestionAnswerService questionAnswerService;


    private Long id;
    private UUID uuid;
    private Long question_id;
    private UUID question_uuid;
    private Long answer_id;
    private UUID answer_uuid;
    private Question question;
    private Answer answer;
    private QuestionAnswer questionAnswer;
    private QuestionAnswer questionAnswerUpdate;
    private QuestionAnswerDTO questionAnswerDTO;
    private QuestionAnswerDTO questionAnswerDTOUpdate;

    private final List<Category> testCategories = Arrays.asList(HISTORY,GENERAL_KNOWLEDGE);


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        id = new Random().nextLong(100000);
        uuid = UUID.randomUUID();

        //Question
        question_id = new Random().nextLong(100000);
        question_uuid = UUID.randomUUID();
        question = new Question(question_id,question_uuid,"Test Question ?", Difficulty.EASY, testCategories);

        //Answer
        answer_id = new Random().nextLong(100000);
        answer_uuid = UUID.randomUUID();
        answer = new Answer(id,uuid,"Test Answer");

        questionAnswer = new QuestionAnswer(id,uuid,question,answer,true);
        questionAnswerUpdate = new QuestionAnswer(id,uuid,question,answer,false);
        questionAnswerDTO = new QuestionAnswerDTO(uuid,question_uuid,answer_uuid,questionAnswer.isCorrect());
        questionAnswerDTOUpdate = new QuestionAnswerDTO(uuid,question_uuid,answer_uuid,questionAnswerUpdate.isCorrect());
    }

    @Test
    void createQuestionAnswer() {
        when(questionService.getbyUUID(questionAnswerDTO.question_uuid())).thenReturn(question);
        when(answerService.getbyUUID(questionAnswerDTO.answer_uuid())).thenReturn(answer);
        when(questionAnswerRepository.save(any(QuestionAnswer.class))).thenReturn(questionAnswer);
        when(questionAnswerMapper.entityToDto(any(QuestionAnswer.class), any(Question.class), any(Answer.class)))
                .thenReturn(new QuestionAnswerDTO(questionAnswerDTO.uuid(), questionAnswerDTO.question_uuid(), questionAnswerDTO.answer_uuid(), questionAnswerDTO.correct()));

        QuestionAnswerDTO result = questionAnswerService.createQuestionAnswer(questionAnswerDTO);

        assertNotNull(result);
        assertEquals(questionAnswerDTO.question_uuid(), result.question_uuid());
        assertEquals(questionAnswerDTO.answer_uuid(), result.answer_uuid());
        assertEquals(questionAnswerDTO.correct(), result.correct());

        verify(questionAnswerRepository, times(1)).save(any(QuestionAnswer.class));
        verify(questionService, times(1)).getbyUUID(questionAnswerDTO.question_uuid());
        verify(answerService, times(1)).getbyUUID(questionAnswerDTO.answer_uuid());
    }

    @Test
    void updateQuestionAnswer() {
        when(questionService.getbyUUID(questionAnswerDTO.question_uuid())).thenReturn(question);
        when(answerService.getbyUUID(questionAnswerDTO.answer_uuid())).thenReturn(answer);
        when(questionAnswerRepository.save(any(QuestionAnswer.class))).thenReturn(questionAnswerUpdate);
        when(questionAnswerMapper.entityToDto(any(QuestionAnswer.class), any(Question.class), any(Answer.class)))
                .thenReturn(new QuestionAnswerDTO(questionAnswerDTOUpdate.uuid(), questionAnswerDTOUpdate.question_uuid(), questionAnswerDTOUpdate.answer_uuid(), questionAnswerDTOUpdate.correct()));

        QuestionAnswerDTO result = questionAnswerService.createQuestionAnswer(questionAnswerDTOUpdate);

        assertNotNull(result);
        assertEquals(questionAnswerDTOUpdate.question_uuid(), result.question_uuid());
        assertEquals(questionAnswerDTOUpdate.answer_uuid(), result.answer_uuid());
        assertEquals(questionAnswerDTOUpdate.correct(), result.correct());

        verify(questionAnswerRepository, times(1)).save(any(QuestionAnswer.class));
        verify(questionService, times(1)).getbyUUID(questionAnswerDTOUpdate.question_uuid());
        verify(answerService, times(1)).getbyUUID(questionAnswerDTOUpdate.answer_uuid());

    }

    @Test
    void getQuestionAnswerbyUUID() {
        when(questionAnswerRepository.findByUuid(uuid)).thenReturn(Optional.ofNullable(questionAnswer));
        when(questionAnswerMapper.entityToDto(questionAnswer,question,answer)).thenReturn(questionAnswerDTO);

        QuestionAnswerDTO questionResult = questionAnswerService.getQuestionAnswerbyUUID(uuid);
        assertNotNull(questionResult);
    }

    @Test
    void deleteQuestionAnswerbyUUID() {
        when(questionAnswerRepository.findByUuid(uuid)).thenReturn(Optional.ofNullable(questionAnswer));
        assertAll(() -> questionAnswerService.deleteQuestionAnswerbyUUID(uuid));
    }
}