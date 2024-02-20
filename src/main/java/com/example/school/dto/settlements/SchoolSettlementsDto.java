package com.example.school.dto.settlements;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class SchoolSettlementsDto {

    private long schoolId;
    private String schoolName;
    private List<ParentSettlementsDto> parentSettlements;
}
