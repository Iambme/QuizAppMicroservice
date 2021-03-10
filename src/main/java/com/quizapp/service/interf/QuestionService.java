package com.quizapp.service.interf;

import com.quizapp.entities.dtos.QuestionDto;
import com.quizapp.exceptions.NotFoundException;

import java.util.List;

public interface QuestionService {
    void addQuestion(QuestionDto questionDto);

    void updateQuestion(QuestionDto questionDto);

    QuestionDto findQuestionById(String id) throws NotFoundException;

    List<QuestionDto> findAll();

    void deleteQuestionById(String id);
}
