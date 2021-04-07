package com.quizapp.controller.rest;

import com.quizapp.entities.dtos.QuestionDto;
import com.quizapp.exceptions.NotFoundException;
import com.quizapp.service.interf.QuestionService;
import com.quizapp.service.producer.ProducerService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@AllArgsConstructor
@RequestMapping("question")
public class QuestionRestController {
    private final QuestionService questionService;
    private final ProducerService producerService;

    @GetMapping("/all")
    public ResponseEntity<Set<QuestionDto>> getAll() {
        producerService.produce("looks good");
        return ResponseEntity.ok(questionService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<QuestionDto> getQuestion(@PathVariable String id) throws NotFoundException {
        return ResponseEntity.ok(questionService.findQuestionById(id));
    }

    @PostMapping("/save")
    public ResponseEntity<String> saveQuestion(@Validated @RequestBody QuestionDto questionDto) {
        questionService.addQuestion(questionDto);
        return ResponseEntity.ok("Question successfully added");
    }

    @PutMapping("/update")
    public ResponseEntity<String> updateQuestion(@Validated @RequestBody QuestionDto questionDto) {
        questionService.updateQuestion(questionDto);
        return ResponseEntity.ok("Question successfully updated");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteQuestion(@PathVariable("id") String id) {
        questionService.deleteQuestionById(id);
        return ResponseEntity.ok("Question successfully deleted");
    }
}
