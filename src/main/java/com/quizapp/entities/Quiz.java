package com.quizapp.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Quiz {

    private String id;
    private String title;
    private String subject;
    private Set<Question> questions;
    private Integer userId;
    private String result;

}
