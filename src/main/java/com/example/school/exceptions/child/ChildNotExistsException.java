package com.example.school.exceptions.child;

public class ChildNotExistsException extends RuntimeException{
    public ChildNotExistsException(long id){
        super("Child with id " + id + " not exists");
    }
}
