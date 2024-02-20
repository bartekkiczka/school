package com.example.school.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AttendanceDto {

    private long id;
    private long childId;
    private LocalDateTime entryDate;
    private LocalDateTime exitDate;
}
