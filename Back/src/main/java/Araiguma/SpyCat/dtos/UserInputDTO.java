package Araiguma.SpyCat.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UserInputDTO(
    @NotBlank
    String username,
    @NotBlank
    String password,
    @NotBlank
    @Email
    String email
){}
