package com.example.school.repository;

import com.example.school.model.Attendance;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AttendanceRepository extends JpaRepository<Attendance, Long> {

    Page<Attendance> findAll(Pageable pageable);

    List<Attendance> findByChildId(long childId);
}
