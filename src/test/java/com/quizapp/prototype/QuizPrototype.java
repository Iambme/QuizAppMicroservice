package com.quizapp.prototype;

import com.quizapp.entities.dtos.QuizDto;

import static com.quizapp.prototype.QuestionPrototype.getQuestionsDto;

public class QuizPrototype {
    public static QuizDto getQuizDtoFullParam() {
        return QuizDto.builder()
                .id("1")
                .title("test_title")
                .description("test_description")
                .subject("test_subject")
                .questions(getQuestionsDto())
                .userId(1)
                .build();
    }


}
