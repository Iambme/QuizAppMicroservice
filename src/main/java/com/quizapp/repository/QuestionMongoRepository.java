package com.quizapp.repository;

import com.quizapp.entities.Question;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface QuestionMongoRepository extends MongoRepository<Question,String> {
}
