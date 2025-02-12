package fr.acajou.quiz.controller;

import fr.acajou.quiz.domain.Category;
import fr.acajou.quiz.domain.Difficulty;
import fr.acajou.quiz.domain.Question;
import fr.acajou.quiz.dto.AnswerDTO;
import fr.acajou.quiz.dto.QuestionDTO;
import fr.acajou.quiz.dto.mapper.QuestionMapper;
import fr.acajou.quiz.service.impl.AnswerService;
import fr.acajou.quiz.service.impl.QuestionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.*;

import static fr.acajou.quiz.domain.Category.GENERAL_KNOWLEDGE;
import static fr.acajou.quiz.domain.Category.HISTORY;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class QuestionControllerTest {

    private MockMvc mockMvc;

    @Mock
    QuestionService questionService;

    @Mock
    QuestionMapper questionMapper;

    @InjectMocks
    private QuestionController questionController;

    private Long id;
    private UUID question_uuid;
    private Question question;
    private QuestionDTO questionDTO;
    private QuestionDTO questionUpdateDTO;
    private List<Category> testCategories = new ArrayList<>();

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(questionController).build();

        id = new Random().nextLong(100000);
        question_uuid = UUID.randomUUID();
        testCategories = Arrays.asList(HISTORY,GENERAL_KNOWLEDGE);
        question = new Question(id,question_uuid,"Test Question ?", Difficulty.EASY, testCategories);
        questionDTO = new QuestionDTO(question_uuid, "Question Test?", Difficulty.EASY,testCategories);
        questionUpdateDTO = new QuestionDTO(question_uuid, "Question Test Update?", Difficulty.EASY,testCategories);
    }

    @Test
    void createQuestion() throws Exception{
        when(questionService.createQuestion(any(QuestionDTO.class))).thenReturn(questionDTO);

        mockMvc.perform(post("/question")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                    "value": "Question Test?",
                                    "difficulty": "EASY",
                                    "categories": [\"HISTORY\", \"GENERAL_KNOWLEDGE\"]
                                }
                                """))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.value").value("Question Test?"));
    }

    @Test
    void updateAnswer() throws Exception{
        when(questionService.createQuestion(any(QuestionDTO.class))).thenReturn(questionUpdateDTO);

        mockMvc.perform(post("/question")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(String.format("""
                                {
                                    "uuid": "%s",
                                    "value": "Question Test Update?",
                                    "difficulty": "EASY",
                                    "categories": ["HISTORY", "GENERAL_KNOWLEDGE"]
                                }
                                """, question_uuid.toString())))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.value").value("Question Test Update?"));
    }

    @Test
    void getQuestion() throws Exception{
        when(questionService.getQuestionbyUUID(question_uuid)).thenReturn(questionDTO);

        mockMvc.perform(get("/question/{uuid}", question_uuid))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.uuid").value(question_uuid.toString()))
                .andExpect(jsonPath("$.value").value("Question Test?"));
    }

    @Test
    void deleteQuestion() throws Exception{

        doNothing().when(questionService).deleteQuestionbyUUID(question_uuid);

        mockMvc.perform(delete("/question/{uuid}", question_uuid))
                .andExpect(status().isNoContent());
    }
}