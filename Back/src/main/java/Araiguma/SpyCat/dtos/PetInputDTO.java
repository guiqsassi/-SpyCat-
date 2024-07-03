package Araiguma.SpyCat.dtos;

import java.util.List;

import Araiguma.SpyCat.Enum.Status;
import Araiguma.SpyCat.Models.User;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record PetInputDTO(
    @NotNull
    Long id,
    @NotBlank
    String color,
    @NotBlank
    String specie,
    @NotBlank
    String description,
    @NotBlank
    String city,
    @NotBlank
    String state,
    
    @Enumerated(EnumType.STRING)
    Status status,
    
    List<ImageInputDTO> images,
    @NotNull
    LocationInputDTO location,
    User user
    
) {
    
}
