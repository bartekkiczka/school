package com.example.school.service;

import com.example.school.exceptions.child.ChildNotExistsException;
import com.example.school.model.Child;
import com.example.school.repository.ChildRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ChildService {

    private final ChildRepository childRepository;
    private final SchoolService schoolService;
    private final ParentService parentService;

    public Page<Child> findAll(Pageable pageable){
        return childRepository.findAll(pageable);
    }

    public Child findById(long id){
        return childRepository.findById(id).orElseThrow(() -> new ChildNotExistsException(id));
    }

    public List<Child> findBySchoolId(long schoolId){
        return childRepository.findBySchoolId(schoolId);
    }

    public List<Child> findByParentId(long parentId){
        return childRepository.findByParentId(parentId);
    }

    public Child save(Child child){
        return childRepository.save(child);
    }
}
