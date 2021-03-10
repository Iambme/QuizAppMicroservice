package com.quizapp.service.interf;

import com.quizapp.entities.dtos.QuestionDto;
import com.quizapp.entities.dtos.QuizDto;
import com.quizapp.exceptions.NotFoundException;

import java.util.List;

public interface QuizService {
    void addQuiz(QuizDto quizDto);

    void addQuestionToQuizById(String quizId, QuestionDto questionDto) throws NotFoundException;

    QuizDto findQuizById(String id) throws NotFoundException;

    List<QuizDto> findAll();

    List<QuizDto> findQuizByUserId(Integer userId);

    void updateQuiz(QuizDto quizDto);

    void deleteQuizById(String id);

    void deleteQuizByUserId(Integer userId);
}
