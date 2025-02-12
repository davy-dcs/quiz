package fr.acajou.quiz.controller;

import fr.acajou.quiz.dto.AnswerDTO;
import fr.acajou.quiz.service.impl.AnswerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class AnswerControllerTest {

    private MockMvc mockMvc;

    @Mock
    AnswerService answerService;

    @InjectMocks
    private AnswerController answerController;

    private UUID answer_uuid;
    private AnswerDTO answerDTO;
    private AnswerDTO answerUpdateDTO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(answerController).build();

        answer_uuid = UUID.randomUUID();
        answerDTO = new AnswerDTO(answer_uuid, "Answer Test");
        answerUpdateDTO = new AnswerDTO(answer_uuid, "Answer Test Update");
    }

    @Test
    void createAnswer() throws Exception{
        when(answerService.createAnswer(any(AnswerDTO.class))).thenReturn(answerDTO);

        mockMvc.perform(post("/answer")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"value\":\"Answer Test\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.value").value("Answer Test"));
    }

    @Test
    void updateAnswer() throws Exception{
        when(answerService.createAnswer(any(AnswerDTO.class))).thenReturn(answerUpdateDTO);

        mockMvc.perform(post("/answer")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"value\":\"Answer Test Update\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.value").value("Answer Test Update"));
    }

    @Test
    void getAnswer() throws Exception{
        when(answerService.getAnswerbyUUID(answer_uuid)).thenReturn(answerDTO);

        mockMvc.perform(get("/answer/{uuid}", answer_uuid))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.uuid").value(answer_uuid.toString()))
                .andExpect(jsonPath("$.value").value("Answer Test"));
    }

    @Test
    void deleteAnswer() throws Exception{

        doNothing().when(answerService).deleteAnswerbyUUID(answer_uuid);

        mockMvc.perform(delete("/answer/{uuid}", answer_uuid))
                .andExpect(status().isNoContent());
    }
}