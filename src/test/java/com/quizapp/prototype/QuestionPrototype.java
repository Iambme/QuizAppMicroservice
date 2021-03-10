package com.quizapp.prototype;

import com.quizapp.entities.dtos.QuestionDto;
import lombok.experimental.UtilityClass;

import java.util.HashSet;
import java.util.Set;

import static com.quizapp.prototype.AnswerPrototype.getAnswersDto;

@UtilityClass
public class QuestionPrototype {
    public static QuestionDto getQuestionDtoFullParam() {
        return QuestionDto.builder()
                .title("test_title")
                .description("test_description")
                .image("test_image")
                .answers(getAnswersDto())
                .build();
    }

    public static Set<QuestionDto> getQuestionsDto() {
        return new HashSet<>() {{
            add(getQuestionDtoFullParam());
            add(QuestionDto.builder()
                    .title("test_title1")
                    .description("test_description2")
                    .answers(getAnswersDto())
                    .build());
        }};
    }
}
