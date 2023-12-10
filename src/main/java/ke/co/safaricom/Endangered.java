package com.ke.safaricom;
import com.ke.safaricom.interfaces.Wildlife;
import org.sql2o.Connection;
import java.util.List;
import java.util.Objects;
public class Endangered extends Wildlife {
    private String age;
    public int id;
    private String health;
    public static final String DATABASE_TYPE = "endangered";
    private String name;


    public Endangered(String age, String health, String name) {
        this.age = age;
        this.health = health;
        type = DATABASE_TYPE;
        this.name=name;

    }

    public void save() {
        try (Connection con = DB.sql2o.open()) {
            String sql = "INSERT INTO animals (name,type,age,health) VALUES(:name,:type,:age,:health)";
            this.id = (int) con.createQuery(sql, true)
                    .addParameter("name", this.name)
                    .addParameter("type", this.type)
                    .addParameter("age", this.age)
                    .addParameter("health", this.health)
                    .executeUpdate()
                    .getKey();

        }

    }

    public static List<Endangered> all() {
        String sql = "SELECT * FROM animals WHERE type='endangered';";
        try (Connection con = DB.sql2o.open()) {
            return con.createQuery(sql)
                    .throwOnMappingFailure(false)
                    .executeAndFetch(Endangered.class);
        }

    }


    public static Endangered find(int id) {
        try (Connection con = DB.sql2o.open()) {
            String sql = "SELECT * FROM animals WHERE id=:id";
            return con.createQuery(sql)
                    .throwOnMappingFailure(false)
                    .addParameter("id", id)
                    .executeAndFetchFirst(Endangered.class);
        }
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHealth() {
        return health;
    }

    public void setHealth(String health) {
        this.health = health;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Endangered endangered = (Endangered) o;
        return id == endangered.id &&
                Objects.equals(age, endangered.age) &&
                Objects.equals(health, endangered.health) &&
                Objects.equals(type, endangered.type) &&
                Objects.equals(name, endangered.name);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(age, id, health, type);
    }
}