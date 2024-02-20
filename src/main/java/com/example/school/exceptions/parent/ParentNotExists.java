package com.example.school.exceptions.parent;

public class ParentNotExists extends RuntimeException {
    public ParentNotExists(long id) {
        super("Parent with id " + id + " not exists");
    }
}
