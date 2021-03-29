package com.quizapp.service.impl;

import com.quizapp.entities.Question;
import com.quizapp.entities.dtos.QuestionDto;
import com.quizapp.exceptions.NotFoundException;
import com.quizapp.repository.QuestionMongoRepository;
import com.quizapp.service.interf.ConverterDto;
import com.quizapp.service.interf.QuestionService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class QuestionServiceImpl implements QuestionService {
    private final QuestionMongoRepository questionMongoRepository;
    private final ConverterDto<Question, QuestionDto> questionConverterDto;

    @Override
    public void addQuestion(QuestionDto questionDto) {
        questionMongoRepository.save(questionConverterDto.fromDtoToEntity(questionDto));
    }

    @Override
    public void updateQuestion(QuestionDto questionDto) {
        questionMongoRepository.save(questionConverterDto.fromDtoToEntity(questionDto));
    }

    @Override
    public QuestionDto findQuestionById(String id) throws NotFoundException {
        return questionConverterDto.fromEntityToDto(questionMongoRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Question not found with id: " + id)));
    }

    @Override
    public Set<QuestionDto> findAll() {
        return questionMongoRepository.findAll()
                .stream()
                .map(questionConverterDto::fromEntityToDto)
                .collect(Collectors.toSet());
    }

    @Override
    public void deleteQuestionById(String id) {
        questionMongoRepository.deleteById(id);
    }


}
