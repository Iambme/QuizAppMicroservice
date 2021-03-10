package com.quizapp.controller.rest;

import com.quizapp.entities.dtos.AnswerDto;
import com.quizapp.exceptions.NotFoundException;
import com.quizapp.service.interf.AnswerService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@AllArgsConstructor
@RequestMapping("/answer")
public class AnswerRestController {
    private final AnswerService answerService;

    @GetMapping("/allByQuestion/{id}")
    public ResponseEntity<Set<AnswerDto>> getAllByQuestion(@PathVariable String id) throws NotFoundException {
        return ResponseEntity.ok(answerService.findAnswersByQuestionId(id));
    }
    @GetMapping("/{id}")
    public ResponseEntity<AnswerDto> getAnswer(@PathVariable String id) throws NotFoundException {
        return ResponseEntity.ok(answerService.findAnswerById(id));
    }

    @PostMapping("/save/{questionId}")
    public ResponseEntity<String> save(@Validated @RequestBody AnswerDto answerDto, @PathVariable String questionId) throws NotFoundException {
        answerService.addAnswerToQuestion(answerDto, questionId);
        return ResponseEntity.ok("Answer successfully added");
    }

    @PutMapping("/update")
    public ResponseEntity<String> update(@Validated @RequestBody AnswerDto answerDto) {
        answerService.updateAnswer(answerDto);
        return ResponseEntity.ok("Answer successfully updated");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteQuiz(@PathVariable("id") String id) {
        answerService.deleteAnswerById(id);
        return ResponseEntity.ok("Answer successfully deleted");
    }
}
