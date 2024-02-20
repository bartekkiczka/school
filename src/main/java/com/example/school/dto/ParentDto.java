package com.example.school.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ParentDto {

    private long id;
    private String firstName;
    private String lastName;
}
