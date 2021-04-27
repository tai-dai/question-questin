package org.launchcode.questionquestin.models.data;

import org.launchcode.questionquestin.models.Quiz;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;
import java.util.List;

public interface QuizRepository extends CrudRepository<Quiz, Integer>{

    List<Quiz> findAll();
}