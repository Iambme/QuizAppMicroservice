package com.quizapp.controller.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.quizapp.service.interf.AnswerService;
import com.quizapp.service.interf.QuestionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static com.quizapp.prototype.AnswerPrototype.getAnswersDto;
import static com.quizapp.prototype.AnswerPrototype.getIncorrectAnswerWithIdDto;
import static com.quizapp.prototype.QuestionPrototype.getQuestionDtoFullParam;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class AnswerRestControllerTest {
    private final AnswerService answerService = mock(AnswerService.class);
    private final QuestionService questionService = mock(QuestionService.class);
    private MockMvc mockMvc;
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(new AnswerRestController(answerService)).build();
        objectMapper = new ObjectMapper();
    }

    @Test
    void getAllByQuestion() throws Exception {
        when(answerService.findAnswersByQuestionId("1")).thenReturn(getAnswersDto());
        mockMvc.perform(get("/answer/allByQuestion/{id}", 1).contentType(MediaType.APPLICATION_JSON)
                .param("id", "1")
                .content(objectMapper.writeValueAsString(getAnswersDto())))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(objectMapper.writeValueAsString(getAnswersDto())));
        verify(answerService, times(1)).findAnswersByQuestionId("1");
    }

    @Test
    void getAnswer() throws Exception {
        when(answerService.findAnswerById("1")).thenReturn(getIncorrectAnswerWithIdDto());
        mockMvc.perform(get("/answer/{id}", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(getIncorrectAnswerWithIdDto())))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(objectMapper.writeValueAsString(getIncorrectAnswerWithIdDto())));
        verify(answerService, times(1)).findAnswerById("1");
    }

    @Test
    void save() throws Exception {
        when(questionService.findQuestionById("1")).thenReturn(getQuestionDtoFullParam());
        mockMvc.perform(post("/answer/save/{questionId}", 1)
                .param("answerDto", objectMapper.writeValueAsString(getIncorrectAnswerWithIdDto()))
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(getIncorrectAnswerWithIdDto())))
                .andExpect(status().isOk());
        verify(answerService, times(1)).addAnswerToQuestion(getIncorrectAnswerWithIdDto(), "1");
    }

    @Test
    void update() throws Exception {
        mockMvc.perform(put("/answer/update")
                .param("answerDto", objectMapper.writeValueAsString(getIncorrectAnswerWithIdDto()))
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(getIncorrectAnswerWithIdDto())))
                .andExpect(status().isOk());
        verify(answerService, times(1)).updateAnswer(getIncorrectAnswerWithIdDto());
    }

    @Test
    void deleteQuiz() throws Exception {
        mockMvc.perform(delete("/answer/delete/{id}", 1))
                .andExpect(status().isOk());
        verify(answerService,times(1)).deleteAnswerById("1");
    }
}