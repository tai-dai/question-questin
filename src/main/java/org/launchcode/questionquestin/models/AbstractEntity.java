package org.launchcode.questionquestin.models;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.util.Objects;

@MappedSuperclass
public abstract class AbstractEntity {

    @Id
    @GeneratedValue
//    Ids currently always evaluate to 0
    private int id;
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
        AbstractEntity abstractEntity = (AbstractEntity) o;
        return id == abstractEntity.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}
