package org.launchcode.questionquestin.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public abstract class AbstractEntity {

    @Id
    private int id;
    private static int nextId = 1;
    private String name;

    public AbstractEntity() {
        id = nextId;
        nextId++;
    }

//  Getters 'n' setters 'n' override methods

    public int getId() { return id; }
    public String getName() { return name; }

    public void setName(String name) { this.name = name; }


    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbstractEntity that = (AbstractEntity) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}
