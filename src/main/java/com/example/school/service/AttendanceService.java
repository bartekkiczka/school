package com.example.school.service;

import com.example.school.exceptions.attendance.AttendanceNotExistsException;
import com.example.school.exceptions.attendance.ExitDateIsBeforeEntryDateException;
import com.example.school.exceptions.attendance.WrongAttendanceTimeException;
import com.example.school.model.Attendance;
import com.example.school.repository.AttendanceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AttendanceService {

    private final AttendanceRepository attendanceRepository;

    public Page<Attendance> findAll(Pageable pageable) {
        return attendanceRepository.findAll(pageable);
    }

    public Attendance findById(long id) {
        return attendanceRepository.findById(id).orElseThrow(AttendanceNotExistsException::new);
    }

    public List<Attendance> findByChildId(long id){
        return attendanceRepository.findByChildId(id);
    }

    public Attendance save(Attendance attendance) {
        if (attendance.getExitDate().isBefore(attendance.getEntryDate())) {
            throw new ExitDateIsBeforeEntryDateException();
        }
        if (attendance.getEntryDate().getHour() < 6 || attendance.getExitDate().getHour() > 16
                || ChronoUnit.DAYS.between(attendance.getEntryDate(), attendance.getExitDate()) > 0) {
            throw new WrongAttendanceTimeException();
        }
        return attendanceRepository.save(attendance);
    }
}
