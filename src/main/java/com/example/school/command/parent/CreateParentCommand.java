package com.example.school.command.parent;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CreateParentCommand {

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;
}
