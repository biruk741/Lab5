package com.company.classes;

import com.company.Main;

import java.util.Arrays;
import java.util.Objects;

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

    public boolean nameEquals(String s){
        Main.counter++;
        return this.name.equals(s);
    }

    @Override
    public boolean equals(Object o) {
        Main.counter++;
        return super.equals(o);
    }
}
