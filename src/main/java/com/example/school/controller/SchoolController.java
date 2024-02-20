package com.example.school.controller;

import com.example.school.command.school.CreateSchoolCommand;
import com.example.school.dto.SchoolDto;
import com.example.school.model.School;
import com.example.school.service.SchoolService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/schools")
public class SchoolController {

    private final SchoolService schoolService;
    private final ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity<List<SchoolDto>> findAll(@PageableDefault(size = 10, page = 0) Pageable pageable){
        List<SchoolDto> schools = schoolService.findAll(pageable).stream()
                .map(school -> modelMapper.map(school, SchoolDto.class)).toList();
        return new ResponseEntity<>(schools, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SchoolDto> findById(@PathVariable long id){
        School school = schoolService.findById(id);
        return new ResponseEntity<>(modelMapper.map(school, SchoolDto.class), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<SchoolDto> save(@RequestBody CreateSchoolCommand command){
        School schoolToAdd = modelMapper.map(command, School.class);
        System.out.println("schoolToAdd: " + schoolToAdd);
        School school = schoolService.save(schoolToAdd);
        System.out.println("school: " + school);
        return new ResponseEntity<>(modelMapper.map(school, SchoolDto.class), HttpStatus.CREATED);
    }
}
