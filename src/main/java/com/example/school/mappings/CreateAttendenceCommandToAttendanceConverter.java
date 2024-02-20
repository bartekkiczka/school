package com.example.school.mappings;

import com.example.school.model.Child;
import com.example.school.service.ChildService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.Converter;
import com.example.school.command.attendance.CreateAttendanceCommand;
import com.example.school.model.Attendance;
import org.modelmapper.spi.MappingContext;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateAttendenceCommandToAttendanceConverter implements Converter<CreateAttendanceCommand, Attendance> {

    private final ChildService childService;

    @Override
    public Attendance convert(MappingContext<CreateAttendanceCommand, Attendance> mappingContext) {
        CreateAttendanceCommand command = mappingContext.getSource();
        Child child = childService.findById(command.getChildId());
        return Attendance.builder()
                .child(child)
                .entryDate(command.getEntryDate())
                .exitDate(command.getExitDate())
                .build();
    }
}
