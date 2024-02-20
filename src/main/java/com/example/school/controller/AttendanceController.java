package com.example.school.controller;

import com.example.school.command.attendance.CreateAttendanceCommand;
import com.example.school.exceptions.attendance.WrongAttendanceTimeException;
import com.example.school.dto.AttendanceDto;
import com.example.school.model.Attendance;
import com.example.school.service.AttendanceService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.temporal.ChronoUnit;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/attendances")
public class AttendanceController {

    private final AttendanceService attendanceService;
    private final ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity<List<AttendanceDto>> findAll(@PageableDefault(size = 10, page = 0) Pageable pageable) {
        List<AttendanceDto> attendances = attendanceService.findAll(pageable).stream()
                .map(attendance -> modelMapper.map(attendance, AttendanceDto.class)).toList();
        return new ResponseEntity<>(attendances, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AttendanceDto> findById(@PathVariable long id) {
        Attendance attendance = attendanceService.findById(id);
        return new ResponseEntity<>(modelMapper.map(attendance, AttendanceDto.class), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<AttendanceDto> save(@RequestBody CreateAttendanceCommand command) {
        Attendance attendanceToAdd = modelMapper.map(command, Attendance.class);
        Attendance attendance = attendanceService.save(attendanceToAdd);
        System.out.println(attendance.getEntryDate().getHour());
        return new ResponseEntity<>(modelMapper.map(attendance, AttendanceDto.class), HttpStatus.CREATED);
    }

}
