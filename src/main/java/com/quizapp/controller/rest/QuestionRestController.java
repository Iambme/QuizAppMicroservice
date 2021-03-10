package com.quizapp.controller.rest;

import com.quizapp.entities.dtos.QuestionDto;
import com.quizapp.exceptions.NotFoundException;
import com.quizapp.service.interf.QuestionService;
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

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("question")
public class QuestionRestController {
    private final QuestionService questionService;

    @GetMapping("/all")
    public ResponseEntity<List<QuestionDto>> getAll() {
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
