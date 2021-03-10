package com.quizapp.utils;

import com.quizapp.entities.Quiz;
import com.quizapp.entities.dtos.QuizDto;
import com.quizapp.service.interf.ConverterDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class QuizDtoConverter implements ConverterDto<Quiz, QuizDto> {
    private final QuestionDtoConverter answerConverterDto;

    @Override
    public Quiz fromDtoToEntity(QuizDto dto) {
        return Quiz.builder()
                .id(dto.getId())
                .title(dto.getTitle())
                .subject(dto.getSubject())
                .userId(dto.getUserId())
                .questions(dto.getQuestions()
                        .stream()
                        .map(answerConverterDto::fromDtoToEntity)
                        .collect(Collectors.toSet()))
                .result(dto.getDescription())
                .build();
    }

    @Override
    public QuizDto fromEntityToDto(Quiz entity) {
        return QuizDto.builder()
                .id(entity.getId())
                .title(entity.getTitle())
                .subject(entity.getSubject())
                .userId(entity.getUserId())
                .questions(entity.getQuestions()
                        .stream()
                        .map(answerConverterDto::fromEntityToDto)
                        .collect(Collectors.toSet()))
                .description(entity.getResult())
                .build();
    }
}
