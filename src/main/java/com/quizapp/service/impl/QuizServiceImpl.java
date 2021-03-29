package com.quizapp.service.impl;

import com.quizapp.entities.Quiz;
import com.quizapp.entities.dtos.QuestionDto;
import com.quizapp.entities.dtos.QuizDto;
import com.quizapp.exceptions.NotFoundException;
import com.quizapp.repository.QuizMongoRepository;
import com.quizapp.service.interf.ConverterDto;
import com.quizapp.service.interf.QuizService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@Service
@AllArgsConstructor
public class QuizServiceImpl implements QuizService {

    private final QuizMongoRepository quizMongoRepository;
    private final ConverterDto<Quiz, QuizDto> quizConverterDto;


    @Override
    public void addQuiz(QuizDto quizDto) {
        quizMongoRepository.save(quizConverterDto.fromDtoToEntity(quizDto));
    }

    @Override
    public void addQuestionToQuizById(String quizId, QuestionDto questionDto) throws NotFoundException {
        QuizDto quizDto = findQuizById(quizId);
        Set<QuestionDto> questions = quizDto.getQuestions();
        questions.add(questionDto);
        quizDto.setQuestions(questions);
        updateQuiz(quizDto);
    }

    @Override
    public QuizDto findQuizById(String id) throws NotFoundException {
        return quizConverterDto.fromEntityToDto(quizMongoRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Quiz not found with id: " + id)));
    }

    @Override
    public List<QuizDto> findAll() {
        return quizMongoRepository.findAll()
                .stream()
                .map(quizConverterDto::fromEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<QuizDto> findQuizByUserId(Integer userId) {

        return quizMongoRepository.findByUserId(userId).stream()
                .map(quizConverterDto::fromEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public void updateQuiz(QuizDto quizDto) {
        quizMongoRepository.save(quizConverterDto.fromDtoToEntity(quizDto));

    }

    @Override
    public void deleteQuizById(String id) {
        quizMongoRepository.deleteById(id);
    }

    @Override
    public void deleteQuizByUserId(Integer userId) {
        quizMongoRepository.deleteQuizzesByUserId(userId);
    }


}
