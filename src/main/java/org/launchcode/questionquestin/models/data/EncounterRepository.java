package org.launchcode.questionquestin.models.data;

import org.launchcode.questionquestin.models.Encounter;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EncounterRepository extends CrudRepository<Encounter, Integer> {
}
