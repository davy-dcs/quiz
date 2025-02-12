package fr.acajou.quiz.service.impl;

import fr.acajou.quiz.domain.Category;
import fr.acajou.quiz.domain.Difficulty;
import fr.acajou.quiz.domain.Question;
import fr.acajou.quiz.dto.QuestionDTO;
import fr.acajou.quiz.dto.mapper.QuestionMapper;
import fr.acajou.quiz.repository.IQuestionRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;


import static fr.acajou.quiz.domain.Category.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


class QuestionServiceTest {

    @Mock
    private IQuestionRepository questionRepository;

    @Mock
    QuestionMapper questionMapper;

    @InjectMocks
    private QuestionService questionService;

    private Long id;
    private UUID uuid;
    private Question question;
    private Question questionUpdate;
    private QuestionDTO questionDTO;
    private QuestionDTO questionDTOUpdate;
    private List<Question> testQuestionList = new ArrayList<>();
    private List<QuestionDTO> testQuestionDTOList = new ArrayList<>();
    private final List<Category> testCategories = Arrays.asList(HISTORY,GENERAL_KNOWLEDGE);

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        id = new Random().nextLong(100000);
        uuid = UUID.randomUUID();
        question = new Question(id,uuid,"Test Question ?", Difficulty.EASY, testCategories);
        questionUpdate = new Question(id,uuid,"Test Question Update?", Difficulty.EASY, testCategories);
        questionDTO = new QuestionDTO(uuid,"Test Question ?", Difficulty.EASY, testCategories);
        questionDTOUpdate = new QuestionDTO(uuid,"Test Question Update?", Difficulty.EASY, testCategories);
    }

    @Test
    void createQuestion() {
        when(questionMapper.entityToDto(question)).thenReturn(questionDTO);
        when(questionRepository.save(any(Question.class))).thenReturn(question);

        QuestionDTO questionResult = questionService.createQuestion(questionDTO);

        assertEquals(question.getValue(), questionResult.value());
        verify(questionRepository, times(1)).save(any(Question.class));
    }

    @Test
    void updateQuestion() {
        when(questionRepository.findByUuid(uuid)).thenReturn(Optional.ofNullable(question));
        when(questionMapper.dtoToEntity(questionDTOUpdate,id)).thenReturn(questionUpdate);
        when(questionMapper.entityToDto(questionUpdate)).thenReturn(questionDTOUpdate);
        when(questionRepository.save(any(Question.class))).thenReturn(questionUpdate);

        QuestionDTO questionResult = questionService.updateQuestion(questionDTOUpdate);

        assertEquals(questionUpdate.getValue(), questionResult.value());
        verify(questionRepository, times(1)).save(any(Question.class));
    }

    @Test
    void getQuestionbyUUID() {
        when(questionRepository.findByUuid(uuid)).thenReturn(Optional.ofNullable(question));
        when(questionMapper.entityToDto(question)).thenReturn(questionDTO);

        QuestionDTO questionResult = questionService.getQuestionbyUUID(uuid);
        assertNotNull(questionResult);
    }

    @Test
    void getAllQuestion(){
        when(questionRepository.findAllByDifficultyAndCategoryIn(Difficulty.EASY,testCategories)).thenReturn(Optional.ofNullable(testQuestionList));
        when(questionMapper.entityToDto(question)).thenReturn(questionDTO);

        List<QuestionDTO> questionsDTOResult = questionService.getAllQuestion(Difficulty.EASY,testCategories);
        System.out.println(questionsDTOResult);
    }

    @Test
    void deleteQuestionbyUUID() {
        when(questionRepository.findByUuid(uuid)).thenReturn(Optional.ofNullable(question));
        assertAll(() -> questionService.deleteQuestionbyUUID(uuid));
    }
}