package com.company.classes;

public class Entity {
    String name;
    String[] preferences;

    public Entity(String name, String[] preferences) {
        this.name = name;
        this.preferences = preferences;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String[] getPreferences() {
        return preferences;
    }

    public void setPreferences(String[] preferences) {
        this.preferences = preferences;
    }
}
