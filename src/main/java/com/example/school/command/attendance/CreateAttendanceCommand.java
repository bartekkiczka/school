package com.example.school.command.attendance;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class CreateAttendanceCommand {

    @NotNull
    private Long childId;

    @NotNull
    private LocalDateTime entryDate;

    @NotNull
    private LocalDateTime exitDate;
}
