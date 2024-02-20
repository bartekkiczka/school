package com.example.school.exceptions.attendance;

public class AttendanceNotExistsException extends RuntimeException{
    public AttendanceNotExistsException(){
        super("Attendence not exists");
    }
}
