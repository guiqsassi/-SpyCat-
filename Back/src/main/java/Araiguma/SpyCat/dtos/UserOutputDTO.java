package Araiguma.SpyCat.dtos;

import java.util.List;


import Araiguma.SpyCat.Models.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UserOutputDTO(
    @NotNull
    Long id,
    @NotBlank 
    String username,
    @NotBlank 
    String state,
    @NotBlank
    @Email
    String email,
    @NotBlank 
    String city,
    String icon,
    List<PetOutputDTO> pet,
    List<PetOutputDTO> favoritos

    // List<PetOutputDTO> pet
) {
    public UserOutputDTO(User user){
        this(user.getId(), user.getUsernameReal(), user.getState(), user.getEmail(), user.getCity(), user.getIcon(), user.getPets().stream().map(pet -> new PetOutputDTO(pet)).toList(), user.getPetsFavoritos().stream().map(pet -> new PetOutputDTO(pet)).toList() );
    }
}