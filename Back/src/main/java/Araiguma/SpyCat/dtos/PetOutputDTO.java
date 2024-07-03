package Araiguma.SpyCat.dtos;

import java.util.List;

import Araiguma.SpyCat.Models.Pet;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record PetOutputDTO(
    @NotNull
    Long id,
    @NotBlank
    String description,
    @NotBlank
    String color,
    @NotBlank
    String specie,
    @NotBlank
    String city,
    @NotBlank
    String state,
    @NotEmpty
    List<ImageOutputDTO> images,
    UserMessageOutputDTO user,
    List<LocationOutputDTO> locations,
    List<CommentOutputDTO> comments
) {
    public PetOutputDTO(Pet pet){
        this(pet.getId(), pet.getDescription(), pet.getColor(), pet.getSpecie(), pet.getCity(), pet.getState(), pet.getImages().stream().map(image-> new ImageOutputDTO(image)).toList(), new UserMessageOutputDTO(pet.getUser()),  pet.getLocations().stream().map(location -> new LocationOutputDTO(location)).toList(),  pet.getComments().stream().map(comment -> new CommentOutputDTO(comment)).toList() );
    }
}
