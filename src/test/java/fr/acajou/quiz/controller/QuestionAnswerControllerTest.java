package fr.acajou.quiz.controller;

import fr.acajou.quiz.domain.*;
import fr.acajou.quiz.dto.AnswerDTO;
import fr.acajou.quiz.dto.QuestionAnswerDTO;
import fr.acajou.quiz.dto.QuestionDTO;
import fr.acajou.quiz.dto.mapper.QuestionAnswerMapper;
import fr.acajou.quiz.dto.mapper.QuestionMapper;
import fr.acajou.quiz.service.impl.QuestionAnswerService;
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

class QuestionAnswerControllerTest {

    private MockMvc mockMvc;

    @Mock
    QuestionAnswerService questionAnswerService;

    @Mock
    QuestionAnswerMapper questionMapper;

    @InjectMocks
    private QuestionAnswerController questionAnswerController;

    private Long id;
    private Long question_id;
    private Long answer_id;
    private UUID uuid;
    private UUID question_uuid;
    private UUID answer_uuid;

    private QuestionAnswer questionAnswer;
    private QuestionAnswerDTO questionAnswerDTO;
    private QuestionAnswerDTO questionAnswerUpdateDTO;
    boolean correct = false;
    Question question;
    private List<Category> testCategories = new ArrayList<>();
    Answer answer;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(questionAnswerController).build();

        id = new Random().nextLong(100000);
        question_id = new Random().nextLong(100000);
        answer_id = new Random().nextLong(100000);
        uuid = UUID.randomUUID();
        question_uuid = UUID.randomUUID();
        answer_uuid = UUID.randomUUID();
        testCategories = Arrays.asList(HISTORY,GENERAL_KNOWLEDGE);
        question = new Question(question_id,question_uuid,"Test Question ?", Difficulty.EASY, testCategories);
        answer = new Answer(answer_id,answer_uuid,"Answer Test");
        questionAnswer = new QuestionAnswer(id,question_uuid,question,answer,correct);
        questionAnswerDTO = new QuestionAnswerDTO(uuid,question_uuid,answer_uuid,correct);
        questionAnswerUpdateDTO = new QuestionAnswerDTO(question_uuid,question_uuid,answer_uuid,true);
    }

    @Test
    void createQuestionAnswer() throws Exception {
        when(questionAnswerService.createQuestionAnswer(any(QuestionAnswerDTO.class))).thenReturn(questionAnswerDTO);

        mockMvc.perform(post("/questionAnswer")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(String.format("""
                                {
                                    "uuid": "%s",
                                    "question_uuid": "%s",
                                    "answer_uuid": "%s",
                                    "correct": false
                                }
                                """,
                                uuid,
                                question_uuid,
                                answer_uuid)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.correct").value("false"));
    }

    @Test
    void updateQuestionAnswer() throws Exception {
        when(questionAnswerService.createQuestionAnswer(any(QuestionAnswerDTO.class))).thenReturn(questionAnswerUpdateDTO);

        mockMvc.perform(post("/questionAnswer")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(String.format("""
                                {
                                    "uuid": "%s",
                                    "question_uuid": "%s",
                                    "answer_uuid": "%s",
                                    "correct": true
                                }
                                """,
                                uuid,
                                question_uuid,
                                answer_uuid)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.correct").value("true"));
    }

    @Test
    void getQuestionAnswer() throws Exception{
        when(questionAnswerService.getQuestionAnswerbyUUID(uuid)).thenReturn(questionAnswerDTO);

        mockMvc.perform(get("/questionAnswer/{uuid}", uuid))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.uuid").value(uuid.toString()));
    }

    @Test
    void deleteQuestionAnswer() throws Exception{
        doNothing().when(questionAnswerService).deleteQuestionAnswerbyUUID(uuid);

        mockMvc.perform(delete("/questionAnswer/{uuid}", uuid))
                .andExpect(status().isNoContent());
    }
}