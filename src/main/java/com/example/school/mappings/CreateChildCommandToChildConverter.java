package com.example.school.mappings;

import com.example.school.command.child.CreateChildCommand;
import com.example.school.model.Child;
import com.example.school.model.Parent;
import com.example.school.model.School;
import com.example.school.service.ParentService;
import com.example.school.service.SchoolService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.Converter;
import org.modelmapper.spi.MappingContext;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateChildCommandToChildConverter implements Converter<CreateChildCommand, Child> {

    private final ParentService parentService;
    private final SchoolService schoolService;

    @Override
    public Child convert(MappingContext<CreateChildCommand, Child> mappingContext) {
        CreateChildCommand command = mappingContext.getSource();
        Parent parent = parentService.findById(command.getParentId());
        School school = schoolService.findById(command.getSchoolId());

        return Child.builder()
                .firstName(command.getFirstName())
                .lastName(command.getLastName())
                .parent(parent)
                .school(school)
                .build();
    }
}
