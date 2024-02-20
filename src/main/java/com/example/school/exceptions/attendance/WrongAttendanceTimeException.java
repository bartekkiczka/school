package com.example.school.exceptions.attendance;

public class WrongAttendanceTimeException extends RuntimeException{
    public WrongAttendanceTimeException(){
        super("Can't add attendance, school working hours are 6am - 4pm");
    }
}
