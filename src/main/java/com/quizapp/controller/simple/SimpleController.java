package com.quizapp.controller.simple;

import com.quizapp.entities.dtos.QuestionDto;
import com.quizapp.exceptions.NotFoundException;
import com.quizapp.service.interf.QuestionService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@AllArgsConstructor
public class SimpleController {
    private final QuestionService questionService;

    @GetMapping("/")
    public String startPage(Model model) throws NotFoundException {
        QuestionDto questionDto = questionService.findQuestionById("2");
        model.addAttribute("question", questionDto);
        return "index";
    }
}
