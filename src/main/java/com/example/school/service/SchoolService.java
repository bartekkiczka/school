package com.example.school.service;

import com.example.school.exceptions.school.SchoolNotExists;
import com.example.school.model.School;
import com.example.school.repository.SchoolRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SchoolService {

    private final SchoolRepository schoolRepository;

    public Page<School> findAll(Pageable pageable) {
        return schoolRepository.findAll(pageable);
    }

    public School findById(long id){
        return schoolRepository.findById(id).orElseThrow(() -> new SchoolNotExists(id));
    }

    public School save(School school) {
        return schoolRepository.save(school);
    }
}
