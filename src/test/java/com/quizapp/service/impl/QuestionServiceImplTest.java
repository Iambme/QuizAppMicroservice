package com.quizapp.service.impl;

import com.quizapp.entities.Question;
import com.quizapp.entities.dtos.QuestionDto;
import com.quizapp.exceptions.NotFoundException;
import com.quizapp.repository.QuestionMongoRepository;
import com.quizapp.service.interf.ConverterDto;
import com.quizapp.service.interf.QuestionService;
import com.quizapp.utils.AnswerDtoConverter;
import com.quizapp.utils.QuestionDtoConverter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static com.quizapp.prototype.QuestionPrototype.getQuestionDtoFullParam;
import static com.quizapp.prototype.QuestionPrototype.getQuestionsDto;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class QuestionServiceImplTest {
    private final QuestionMongoRepository questionMongoRepository = mock(QuestionMongoRepository.class);
    private ConverterDto<Question, QuestionDto> questionConverterDto;
    private QuestionService questionService;

    @BeforeEach
    public void setup() {
        questionConverterDto = new QuestionDtoConverter(new AnswerDtoConverter());
        questionService = new QuestionServiceImpl(questionMongoRepository, questionConverterDto);
    }


    @Test
    void addQuestion() {
        QuestionDto questionDto = getQuestionDtoFullParam();
        questionService.addQuestion(questionDto);
        verify(questionMongoRepository, times(1)).save(questionConverterDto.fromDtoToEntity(questionDto));
    }

    @Test
    void updateQuestion() {
        questionService.updateQuestion(getQuestionDtoFullParam());
        verify(questionMongoRepository, times(1)).save(questionConverterDto.fromDtoToEntity(getQuestionDtoFullParam()));
    }

    @Test
    void findQuestionById() throws NotFoundException {
        when(questionMongoRepository.findById("1")).thenReturn(Optional.of(questionConverterDto.fromDtoToEntity(getQuestionDtoFullParam())));
        QuestionDto questionDto = questionService.findQuestionById("1");
        assertEquals(questionDto, getQuestionDtoFullParam());
        verify(questionMongoRepository, times(1)).findById("1");
    }

    @Test
    void findAll() {
        when(questionMongoRepository.findAll()).thenReturn(getQuestionsDto().stream()
                .map(x -> questionConverterDto.fromDtoToEntity(x)).collect(Collectors.toList()));
        Set<QuestionDto> questionDtoList = questionService.findAll();
        assertEquals(questionDtoList, getQuestionsDto());
        verify(questionMongoRepository, times(1)).findAll();
    }

    @Test
    void deleteQuestionById() {
        questionService.deleteQuestionById("1");
        verify(questionMongoRepository, times(1)).deleteById("1");
    }
}