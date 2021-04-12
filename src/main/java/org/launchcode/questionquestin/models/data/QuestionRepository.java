package org.launchcode.questionquestin.models.data;

import org.launchcode.questionquestin.models.Question;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionRepository extends CrudRepository<Question, Integer>{
}
