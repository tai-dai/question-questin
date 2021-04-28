package org.launchcode.questionquestin.models.data;

import org.launchcode.questionquestin.models.Quiz;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface QuizRepository extends CrudRepository<Quiz, Integer>{

    @Query(value = "select * from quiz where selected = true", nativeQuery = true)
    public List<Quiz> findBySelected();
}