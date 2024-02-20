package com.example.school.repository;

import com.example.school.model.Child;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChildRepository extends JpaRepository<Child, Long> {

    Page<Child> findAll(Pageable pageable);
    List<Child> findBySchoolId(long schoolId);
    List<Child> findByParentId(long parentId);
}
