package com.example.school.controller;

import com.example.school.command.parent.CreateParentCommand;
import com.example.school.dto.ParentDto;
import com.example.school.model.Parent;
import com.example.school.service.ParentService;
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
@RequestMapping("/parents")
public class ParentController {

    private final ParentService parentService;
    private final ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity<List<ParentDto>> findAll(@PageableDefault(size = 10, page = 0) Pageable pageable) {
        List<ParentDto> parents = parentService.findAll(pageable).stream()
                .map(parent -> modelMapper.map(parent, ParentDto.class)).toList();
        return new ResponseEntity<>(parents, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ParentDto> findById(@PathVariable long id) {
        Parent parent = parentService.findById(id);
        return new ResponseEntity<>(modelMapper.map(parent, ParentDto.class), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ParentDto> save(@RequestBody CreateParentCommand command) {
        Parent parentToAdd = modelMapper.map(command, Parent.class);
        Parent parent = parentService.save(parentToAdd);
        return new ResponseEntity<>(modelMapper.map(parent, ParentDto.class), HttpStatus.CREATED);
    }
}
