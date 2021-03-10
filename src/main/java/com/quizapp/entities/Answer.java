package com.quizapp.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Answer {

    private String id;
    private String text;
    private boolean correct;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Answer)) return false;
        Answer answer = (Answer) o;
        return id.equals(answer.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
