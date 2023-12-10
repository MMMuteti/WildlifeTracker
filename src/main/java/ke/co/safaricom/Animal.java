package com.ke.safaricom;

import com.ke.safaricom.interfaces.Wildlife;
import org.sql2o.Connection;


import java.util.Objects;
import java.util.*;
import java.util.List;

public class Animal extends Wildlife {
    private int id;
    private String name;
    private static final String DATABASE_TYPE = "animal";

    public Animal(String name) {
        this.name = name;
        type = DATABASE_TYPE;
    }

    public void save() {
        try (Connection con = DB.sql2o.open()) {
            String sql = "INSERT INTO animals (name,type) VALUES(:name,:type)";
            this.id = (int) con.createQuery(sql, true)
                    .addParameter("name", this.getName())
                    .addParameter("type", this.type)
                    .executeUpdate()
                    .getKey();

        }

    }

    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    public static List<Animal> all() {
        String sql = "SELECT * FROM animals WHERE type='animal';";
        try (Connection con = DB.sql2o.open()) {
            return con.createQuery(sql)
                    .throwOnMappingFailure(false)
                    .executeAndFetch(Animal.class);

        }
    }

    public static Animal find(int id) {
        try (Connection con = DB.sql2o.open()) {
            String sql = "SELECT * FROM animals WHERE id=:id";
            return con.createQuery(sql)
                    .throwOnMappingFailure(false)
                    .addParameter("id", id)
                    .executeAndFetchFirst(Animal.class);
        }
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Animal animal = (Animal) o;
        return id == animal.id &&
                Objects.equals(name, animal.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}