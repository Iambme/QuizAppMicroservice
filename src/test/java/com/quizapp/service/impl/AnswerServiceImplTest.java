package com.quizapp.service.impl;

import com.quizapp.entities.Answer;
import com.quizapp.entities.dtos.AnswerDto;
import com.quizapp.entities.dtos.QuestionDto;
import com.quizapp.exceptions.NotFoundException;
import com.quizapp.repository.AnswerMongoRepository;
import com.quizapp.service.interf.AnswerService;
import com.quizapp.service.interf.ConverterDto;
import com.quizapp.service.interf.QuestionService;
import com.quizapp.utils.AnswerDtoConverter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.Set;

import static com.quizapp.prototype.AnswerPrototype.*;
import static com.quizapp.prototype.QuestionPrototype.getQuestionDtoFullParam;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;


class AnswerServiceImplTest {
    private final AnswerMongoRepository answerMongoRepository = mock(AnswerMongoRepository.class);
    private final QuestionService questionService = mock(QuestionService.class);
    private ConverterDto<Answer, AnswerDto> answerDtoConverter;
    private AnswerService answerService;

    @BeforeEach
    void setUp() {
        answerDtoConverter = new AnswerDtoConverter();
        answerService = new AnswerServiceImpl(answerMongoRepository, questionService, answerDtoConverter);
    }

    @Test
    void addAnswerToQuestion() throws NotFoundException {
        QuestionDto testQuestion = getQuestionDtoFullParam();
        when(questionService.findQuestionById("1")).thenReturn(testQuestion);
        answerService.addAnswerToQuestion(getIncorrectAnswerWithIdDto(), "1");
        Set<AnswerDto> testAnswers = getQuestionDtoFullParam().getAnswers();
        testAnswers.add(getIncorrectAnswerWithIdDto());
        assertEquals(testAnswers, testQuestion.getAnswers());
        verify(answerMongoRepository, times(1)).save(answerDtoConverter.fromDtoToEntity(getIncorrectAnswerWithIdDto()));
        verify(questionService, times(1)).findQuestionById("1");
    }

    @Test
    void updateAnswer() {
        answerService.updateAnswer(getIncorrectAnswerWithIdDto());
        verify(answerMongoRepository, times(1)).save(answerDtoConverter.fromDtoToEntity(getIncorrectAnswerWithIdDto()));
    }

    @Test
    void findAnswerById() throws NotFoundException {
        when(answerMongoRepository.findById("1")).thenReturn(Optional.of(answerDtoConverter.fromDtoToEntity(getCorrectAnswerDto())));
        AnswerDto testAnswer = answerService.findAnswerById("1");
        assertEquals(testAnswer, getCorrectAnswerDto());
        verify(answerMongoRepository, times(1)).findById("1");
    }

    @Test
    void findAnswersByQuestionId() throws NotFoundException {
    	when(questionService.findQuestionById("1")).thenReturn(getQuestionDtoFullParam());
    	Set<AnswerDto> answerDtos = answerService.findAnswersByQuestionId("1");
    	assertEquals(answerDtos, getAnswersDto());
    	verify(questionService,times(1)).findQuestionById("1");
    }

    @Test
    void deleteAnswerById() {
    	answerService.deleteAnswerById("1");
    	verify(answerMongoRepository,times(1)).deleteById("1");
    }
}