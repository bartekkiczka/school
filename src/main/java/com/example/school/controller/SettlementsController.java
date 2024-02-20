package com.example.school.controller;

import com.example.school.model.Child;
import com.example.school.model.School;
import com.example.school.dto.settlements.ParentSettlementsDto;
import com.example.school.dto.settlements.SchoolSettlementsDto;
import com.example.school.service.ChildService;
import com.example.school.service.SchoolService;
import com.example.school.service.SettlementsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/settlements")
public class SettlementsController {

    private final SettlementsService settlementsService;
    private final ChildService childService;
    private final SchoolService schoolService;

    @GetMapping("/school/{schoolId}/month/{monthNumber}")
    public ResponseEntity<SchoolSettlementsDto> getSchoolSettlements(@PathVariable long schoolId, @PathVariable int monthNumber) {
        School school = schoolService.findById(schoolId);
        List<Child> children = childService.findBySchoolId(schoolId);
        List<ParentSettlementsDto> parentSettlementsResponseList = settlementsService.calculateSchoolSettlements(children, school, monthNumber);
        SchoolSettlementsDto response = SchoolSettlementsDto.builder()
                .schoolId(schoolId)
                .schoolName(school.getName())
                .parentSettlements(parentSettlementsResponseList)
                .build();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/parent/{parentId}/month/{monthNumber}")
    public ResponseEntity<ParentSettlementsDto> getParentSettlements(@PathVariable long parentId, @PathVariable int monthNumber) {
        List<Child> children = childService.findByParentId(parentId);
        ParentSettlementsDto response = settlementsService.calculateParentSettlements(children, parentId, monthNumber);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
