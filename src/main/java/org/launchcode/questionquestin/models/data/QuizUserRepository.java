package org.launchcode.questionquestin.models.data;

import org.launchcode.questionquestin.models.QuizUser;
import org.springframework.data.repository.CrudRepository;

public interface QuizUserRepository extends CrudRepository<QuizUser, Integer> {
}
