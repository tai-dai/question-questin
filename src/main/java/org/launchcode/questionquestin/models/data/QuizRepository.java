package org.launchcode.questionquestin.models.data;

import org.launchcode.questionquestin.models.Quiz;
import org.springframework.data.repository.CrudRepository;

public interface QuizRepository extends CrudRepository<Quiz, Integer>{}