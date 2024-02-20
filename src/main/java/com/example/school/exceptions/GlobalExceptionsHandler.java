package com.example.school.exceptions;

import com.example.school.exceptions.attendance.AttendanceNotExistsException;
import com.example.school.exceptions.attendance.ExitDateIsBeforeEntryDateException;
import com.example.school.exceptions.attendance.WrongAttendanceTimeException;
import com.example.school.exceptions.child.ChildNotExistsException;
import com.example.school.exceptions.parent.ParentNotExists;
import com.example.school.exceptions.school.SchoolNotExists;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionsHandler {

    @ExceptionHandler(ExitDateIsBeforeEntryDateException.class)
    public ResponseEntity<ErrorMessage> handleExitDateIsBeforeEntryDateException(final ExitDateIsBeforeEntryDateException e, WebRequest request) {
        return new ResponseEntity<>(ErrorMessage.builder()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .timeStamp(LocalDateTime.now())
                .message(e.getMessage())
                .description(request.getDescription(false))
                .build(),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(SchoolNotExists.class)
    public ResponseEntity<ErrorMessage> handleSchoolNotExists(final SchoolNotExists e, WebRequest request) {
        return new ResponseEntity<>(ErrorMessage.builder()
                .statusCode(HttpStatus.NOT_FOUND.value())
                .timeStamp(LocalDateTime.now())
                .message(e.getMessage())
                .description(request.getDescription(false))
                .build(),
                HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ParentNotExists.class)
    public ResponseEntity<ErrorMessage> handleParentNotExists(final ParentNotExists e, WebRequest request) {
        return new ResponseEntity<>(ErrorMessage.builder()
                .statusCode(HttpStatus.NOT_FOUND.value())
                .timeStamp(LocalDateTime.now())
                .message(e.getMessage())
                .description(request.getDescription(false))
                .build(),
                HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ChildNotExistsException.class)
    public ResponseEntity<ErrorMessage> handleChildNotExistsException(final ChildNotExistsException e, WebRequest request) {
        return new ResponseEntity<>(ErrorMessage.builder()
                .statusCode(HttpStatus.NOT_FOUND.value())
                .timeStamp(LocalDateTime.now())
                .message(e.getMessage())
                .description(request.getDescription(false))
                .build(),
                HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(AttendanceNotExistsException.class)
    public ResponseEntity<ErrorMessage> handleAttendanceNotExistsException(final AttendanceNotExistsException e, WebRequest request) {
        return new ResponseEntity<>(ErrorMessage.builder()
                .statusCode(HttpStatus.NOT_FOUND.value())
                .timeStamp(LocalDateTime.now())
                .message(e.getMessage())
                .description(request.getDescription(false))
                .build(),
                HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(WrongAttendanceTimeException.class)
    public ResponseEntity<ErrorMessage> handleWrongAttendanceTimeException(final WrongAttendanceTimeException e, WebRequest request) {
        return new ResponseEntity<>(ErrorMessage.builder()
                .statusCode(HttpStatus.NOT_FOUND.value())
                .timeStamp(LocalDateTime.now())
                .message(e.getMessage())
                .description(request.getDescription(false))
                .build(),
                HttpStatus.NOT_FOUND);
    }
}
