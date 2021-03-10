package com.quizapp.service.interf;

import com.quizapp.entities.dtos.AnswerDto;
import com.quizapp.exceptions.NotFoundException;

import java.util.Set;

public interface AnswerService {
    void addAnswerToQuestion(AnswerDto answerDto, String questionId) throws NotFoundException;

    void updateAnswer(AnswerDto answerDto);

    AnswerDto findAnswerById(String id) throws NotFoundException;

    Set<AnswerDto> findAnswersByQuestionId(String questionId) throws NotFoundException;

    void deleteAnswerById(String id);
}
