package com.quizapp.utils;

import com.quizapp.entities.Answer;
import com.quizapp.entities.dtos.AnswerDto;
import com.quizapp.service.interf.ConverterDto;
import org.springframework.stereotype.Component;

@Component
public class AnswerConverterDto implements ConverterDto<Answer, AnswerDto> {
    @Override
    public Answer fromDtoToEntity(AnswerDto dto) {
        return Answer.builder()
                .id(dto.getId())
                .text(dto.getText())
                .correct(dto.isCorrect())
                .build();
    }

    @Override
    public AnswerDto fromEntityToDto(Answer entity) {
        return AnswerDto.builder()
                .id(entity.getId())
                .text(entity.getText())
                .correct(entity.isCorrect())
                .build();
    }
}
