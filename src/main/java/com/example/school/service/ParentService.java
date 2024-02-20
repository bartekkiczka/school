package com.example.school.service;

import com.example.school.exceptions.parent.ParentNotExists;
import com.example.school.model.Parent;
import com.example.school.repository.ParentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ParentService {

    private final ParentRepository parentRepository;

    public Page<Parent> findAll(Pageable pageable){
        return parentRepository.findAll(pageable);
    }

    public Parent findById(long id){
        return parentRepository.findById(id).orElseThrow(() -> new ParentNotExists(id));
    }

    public Parent save(Parent parent){
        return parentRepository.save(parent);
    }
}
