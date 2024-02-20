package com.example.school.controller;

import com.example.school.command.child.CreateChildCommand;
import com.example.school.dto.ChildDto;
import com.example.school.model.Child;
import com.example.school.service.ChildService;
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
@RequestMapping("/children")
public class ChildController {

    private final ChildService childService;
    private final ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity<List<ChildDto>> findAll(@PageableDefault(size = 10, page = 0) Pageable pageable) {
        List<ChildDto> children = childService.findAll(pageable).stream()
                .map(child -> modelMapper.map(child, ChildDto.class)).toList();
        return new ResponseEntity<>(children, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ChildDto> findById(@PathVariable long id) {
        Child child = childService.findById(id);
        return new ResponseEntity<>(modelMapper.map(child, ChildDto.class), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ChildDto> save(@RequestBody CreateChildCommand command) {
        Child childToAdd = modelMapper.map(command, Child.class);
        Child child = childService.save(childToAdd);
        return new ResponseEntity<>(modelMapper.map(child, ChildDto.class), HttpStatus.CREATED);
    }
}
