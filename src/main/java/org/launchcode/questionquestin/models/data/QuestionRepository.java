package org.launchcode.questionquestin.models.data;

import org.launchcode.questionquestin.models.Question;
import org.springframework.data.repository.CrudRepository;

public interface QuestionRepository extends CrudRepository<Question, Integer>{
}
