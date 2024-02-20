package com.example.school.command.child;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CreateChildCommand {

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    @NotNull
    private Long parentId;

    @NotNull
    private Long schoolId;
}
