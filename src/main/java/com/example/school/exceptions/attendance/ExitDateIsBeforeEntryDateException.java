package com.example.school.exceptions.attendance;

public class ExitDateIsBeforeEntryDateException extends RuntimeException {
    public ExitDateIsBeforeEntryDateException() {
        super("Exit date if before entry date");
    }
}
