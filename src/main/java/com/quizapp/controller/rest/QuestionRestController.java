package com.quizapp.controller.rest;

import com.quizapp.entities.dtos.QuestionDto;
import com.quizapp.exceptions.NotFoundException;
import com.quizapp.service.interf.QuestionService;
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

    @GetMapping("/all")
    public ResponseEntity<Set<QuestionDto>> getAll() {
        return ResponseEntity.ok(questionService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<QuestionDto> get(@PathVariable String id) throws NotFoundException {
        return ResponseEntity.ok(questionService.findQuestionById(id));
    }

    @PostMapping("/save")
    public ResponseEntity<String> save(@Validated @RequestBody QuestionDto questionDto) {
        questionService.addQuestion(questionDto);
        return ResponseEntity.ok("Question successfully added");
    }

    @PutMapping("/update")
    public ResponseEntity<String> update(@Validated @RequestBody QuestionDto questionDto) {
        questionService.updateQuestion(questionDto);
        return ResponseEntity.ok("Question successfully updated");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") String id) {
        questionService.deleteQuestionById(id);
        return ResponseEntity.ok("Question successfully deleted");
    }
}
