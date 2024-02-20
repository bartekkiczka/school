package com.example.school.command.school;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CreateSchoolCommand {

    @NotBlank
    private String name;

    @NotNull
    private Float hourPrice;
}
