package Araiguma.SpyCat.dtos;

import java.util.List;

import Araiguma.SpyCat.Enum.Status;
import Araiguma.SpyCat.Models.User;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record LostPetInputDTO(

    Long id,
    @NotBlank
    String description,
    @NotBlank
    String city,
    @NotBlank
    String state,
    @NotBlank
    String email,
    @NotBlank
    String phone,
    @NotBlank
    String color,
    @NotBlank
    String specie,
    @NotNull
    LocationInputDTO location,
    List<ImageInputDTO> images,
    @Enumerated(EnumType.STRING)
    Status status,
    User user
) {
    
}
