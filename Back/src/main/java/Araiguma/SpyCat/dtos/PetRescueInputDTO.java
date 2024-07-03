package Araiguma.SpyCat.dtos;

import Araiguma.SpyCat.Enum.Status;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

public record PetRescueInputDTO (
    Long id,    
    @Enumerated(EnumType.STRING)
    Status status
){
    
}
