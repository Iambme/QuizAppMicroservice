package com.quizapp.repository;

import com.quizapp.entities.Answer;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AnswerMongoRepository extends MongoRepository<Answer,String> {
}
