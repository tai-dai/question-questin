package org.launchcode.questionquestin.models;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import javax.persistence.*;
import java.util.Objects;

@MappedSuperclass
public abstract class AbstractEntity {

    @Id
    @GeneratedValue
    private int id;

    @NotBlank
    @Size(min = 3, max = 400)
    private String name;

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
