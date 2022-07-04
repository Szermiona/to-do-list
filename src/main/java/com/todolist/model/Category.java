package com.todolist.model;

public enum Category {
    GENERAL ("General"),
    WORK ("Work"),
    HOUSEHOLD ("Household"),
    PERSONAL ("Personal");

    private final String name;

    public String getName() {
        return name;
    }

    Category(String name) {
        this.name = name;
    }
}
