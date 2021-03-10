package com.quizapp.service.impl;

import com.quizapp.entities.Answer;
import com.quizapp.entities.dtos.AnswerDto;
import com.quizapp.entities.dtos.QuestionDto;
import com.quizapp.exceptions.NotFoundException;
import com.quizapp.repository.AnswerMongoRepository;
import com.quizapp.service.interf.AnswerService;
import com.quizapp.service.interf.ConverterDto;
import com.quizapp.service.interf.QuestionService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@AllArgsConstructor
public class AnswerServiceImpl implements AnswerService {
    private final AnswerMongoRepository answerMongoRepository;
    private final QuestionService questionService;
    private final ConverterDto<Answer, AnswerDto> answerConverterDto;

    @Override
    public void addAnswerToQuestion(AnswerDto answerDto, String questionId) throws NotFoundException {
        QuestionDto questionDto = questionService.findQuestionById(questionId);
        answerMongoRepository.save(answerConverterDto.fromDtoToEntity(answerDto));
        Set<AnswerDto> answers = questionDto.getAnswers();
        answers.add(answerDto);
        questionDto.setAnswers(answers);
        questionService.updateQuestion(questionDto);
    }

    @Override
    public void updateAnswer(AnswerDto answerDto) {
        answerMongoRepository.save(answerConverterDto.fromDtoToEntity(answerDto));
    }

    @Override
    public AnswerDto findAnswerById(String id) throws NotFoundException {
        return answerConverterDto.fromEntityToDto(answerMongoRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Answer not found with id: " + id)));
    }

    @Override
    public Set<AnswerDto> findAnswersByQuestionId(String questionId) throws NotFoundException {
        return new HashSet<>(questionService.findQuestionById(questionId)
                .getAnswers());
    }

    @Override
    public void deleteAnswerById(String id) {
        answerMongoRepository.deleteById(id);
    }

}
