package Araiguma.SpyCat.dtos;

import Araiguma.SpyCat.Models.LostPet;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record LostPetOutputDTO(
    @NotNull
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
    String phone
) {
    public LostPetOutputDTO(LostPet lostPet){
        this(lostPet.getId(), lostPet.getState(), lostPet.getPhone(), lostPet.getEmail(), lostPet.getDescription(), lostPet.getCity());
    }
}
