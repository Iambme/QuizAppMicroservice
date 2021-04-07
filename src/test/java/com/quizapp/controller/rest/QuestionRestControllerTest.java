package com.quizapp.controller.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.quizapp.service.interf.QuestionService;
import com.quizapp.service.producer.ProducerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static com.quizapp.prototype.QuestionPrototype.getQuestionDtoFullParam;
import static com.quizapp.prototype.QuestionPrototype.getQuestionsDto;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class QuestionRestControllerTest {
    private final QuestionService questionService = mock(QuestionService.class);
    private final ProducerService producerService = mock(ProducerService.class);
    MockMvc mockMvc;
    ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        objectMapper = new ObjectMapper();
        mockMvc = MockMvcBuilders.standaloneSetup(new QuestionRestController(questionService,producerService)).build();
    }

    @Test
    void getAll() throws Exception {
        when(questionService.findAll()).thenReturn(getQuestionsDto());
        mockMvc.perform(get("/question/all").contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(getQuestionsDto())))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(objectMapper.writeValueAsString(getQuestionsDto())));
        verify(questionService, times(1)).findAll();
    }

    @Test
    void getQuestion() throws Exception {
        when(questionService.findQuestionById("1")).thenReturn(getQuestionDtoFullParam());
        mockMvc.perform(get("/question/{id}", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(getQuestionDtoFullParam())))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(objectMapper.writeValueAsString(getQuestionDtoFullParam())));
        verify(questionService, times(1)).findQuestionById("1");
    }

    @Test
    void saveQuestion() throws Exception {
        mockMvc.perform(post("/question/save")
                .param("questionDto", objectMapper.writeValueAsString(getQuestionDtoFullParam()))
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(getQuestionDtoFullParam())))
                .andExpect(status().isOk());
        verify(questionService, times(1)).addQuestion(getQuestionDtoFullParam());
    }

    @Test
    void updateQuestion() throws Exception {
        mockMvc.perform(put("/question/update")
                .param("questionDto", objectMapper.writeValueAsString(getQuestionDtoFullParam()))
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(getQuestionDtoFullParam())))
                .andExpect(status().isOk());
        verify(questionService, times(1)).updateQuestion(getQuestionDtoFullParam());
    }

    @Test
    void deleteQuestion() throws Exception {
        mockMvc.perform(delete("/question/delete/{id}", 1))
                .andExpect(status().isOk());
        verify(questionService, times(1)).deleteQuestionById("1");
    }
}