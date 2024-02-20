package com.example.school.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ChildDto {

    private long id;
    private String firstName;
    private String lastName;
    private long parentId;
    private long SchoolId;
}
