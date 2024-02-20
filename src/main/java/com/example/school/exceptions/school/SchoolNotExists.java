package com.example.school.exceptions.school;

public class SchoolNotExists extends RuntimeException{
    public SchoolNotExists(long id){
        super("School with id " + id + " not exists");
    }
}
