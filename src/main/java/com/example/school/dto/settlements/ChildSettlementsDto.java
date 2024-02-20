package com.example.school.dto.settlements;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ChildSettlementsDto {

    private String childName;
    private String childSurname;
    private float charge;
    private int timeSpent;
}
