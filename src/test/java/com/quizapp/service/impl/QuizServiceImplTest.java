package com.quizapp.service.impl;

import com.quizapp.entities.Question;
import com.quizapp.entities.Quiz;
import com.quizapp.entities.dtos.QuestionDto;
import com.quizapp.entities.dtos.QuizDto;
import com.quizapp.exceptions.NotFoundException;
import com.quizapp.repository.QuizMongoRepository;
import com.quizapp.service.interf.ConverterDto;
import com.quizapp.service.interf.QuizService;
import com.quizapp.utils.AnswerDtoConverter;
import com.quizapp.utils.QuestionDtoConverter;
import com.quizapp.utils.QuizDtoConverter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.quizapp.prototype.QuestionPrototype.getQuestionDtoFullParam;
import static com.quizapp.prototype.QuizPrototype.getQuizDtoFullParam;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class QuizServiceImplTest {
    private final QuizMongoRepository quizMongoRepository = mock(QuizMongoRepository.class);
    private ConverterDto<Quiz, QuizDto> quizDtoConverter;
    private ConverterDto<Question, QuestionDto> questionDtoConverter;
    private QuizService quizService;
    List<QuizDto> quizzes;

    @BeforeEach
    void setup() {
        questionDtoConverter = new QuestionDtoConverter(new AnswerDtoConverter());
        quizDtoConverter = new QuizDtoConverter(questionDtoConverter);
        quizService = new QuizServiceImpl(quizMongoRepository, quizDtoConverter);
        quizzes = new ArrayList<>();
        quizzes.add(getQuizDtoFullParam());
    }

    @Test
    void addQuiz() {
        QuizDto quizDto = getQuizDtoFullParam();
        quizService.addQuiz(quizDto);
        verify(quizMongoRepository, times(1)).save(quizDtoConverter.fromDtoToEntity(quizDto));
    }

    @Test
    void addQuestionToQuizById() throws NotFoundException {
        Quiz quiz = quizDtoConverter.fromDtoToEntity(getQuizDtoFullParam());
        when(quizMongoRepository.findById("1")).thenReturn(Optional.of(quiz));
        QuestionDto questionDto = getQuestionDtoFullParam();
        quizService.addQuestionToQuizById("1", questionDto);
        verify(quizMongoRepository, times(1)).findById("1");
        verify(quizMongoRepository, times(1)).save(quiz);
    }

    @Test
    void findQuizById() throws NotFoundException {
        Quiz quiz = quizDtoConverter.fromDtoToEntity(getQuizDtoFullParam());
        when(quizMongoRepository.findById("1")).thenReturn(Optional.of(quiz));
        QuizDto quizDto = quizService.findQuizById("1");
        assertEquals(quizDto, getQuizDtoFullParam());
        verify(quizMongoRepository, times(1)).findById("1");
    }

    @Test
    void findAll() {

        when(quizMongoRepository.findAll()).thenReturn(quizzes.stream()
                .map(quizDto -> quizDtoConverter.fromDtoToEntity(quizDto)).collect(Collectors.toList()));
        List<QuizDto> quizDtoList = quizService.findAll();
        assertEquals(quizDtoList, quizzes);
        verify(quizMongoRepository, times(1)).findAll();
    }

    @Test
    void findQuizByUserId() {
        when(quizMongoRepository.findByUserId(1)).thenReturn(quizzes.stream()
                .map(quizDto -> quizDtoConverter.fromDtoToEntity(quizDto)).collect(Collectors.toList()));
        List<QuizDto> quizDtoList = quizService.findQuizByUserId(1);
        assertEquals(quizDtoList, quizzes);
        verify(quizMongoRepository, times(1)).findByUserId(1);
    }

    @Test
    void updateQuiz() {
        quizService.updateQuiz(getQuizDtoFullParam());
        verify(quizMongoRepository, times(1)).save(quizDtoConverter.fromDtoToEntity(getQuizDtoFullParam()));
    }

    @Test
    void deleteQuizById() {
        quizService.deleteQuizById("1");
        verify(quizMongoRepository, times(1)).deleteById("1");
    }

    @Test
    void deleteQuizByUserId() {
        quizService.deleteQuizByUserId(1);
        verify(quizMongoRepository, times(1)).deleteQuizzesByUserId(1);
    }
}