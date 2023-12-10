package com.ke.safaricom.interfaces;

import java.util.Arrays;
import java.util.List;

public abstract class Wildlife {

    public int id;
    public String name;
    public String type;
    static List<String> animalHealth = Arrays.asList("Healthy", "ill", "Okay");
    static List<String> animalAge = Arrays.asList("Newborn", "Young", "Adult");

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

}