package org.launchcode.questionquestin.models.data;

import org.launchcode.questionquestin.models.Answer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnswerRepository extends CrudRepository<Answer, Integer>{
}
