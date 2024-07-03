package Araiguma.SpyCat.dtos;

public record PetSightingInputDTO(
    long id,
    LocationInputDTO location,
    ImageInputDTO image
) {
    
}
