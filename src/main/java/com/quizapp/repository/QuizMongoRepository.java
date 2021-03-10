package com.quizapp.repository;

import com.quizapp.entities.Quiz;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface QuizMongoRepository extends MongoRepository<Quiz, String> {
    List<Quiz> findByUserId(Integer userId);

    void deleteQuizzesByUserId(Integer userId);
}
