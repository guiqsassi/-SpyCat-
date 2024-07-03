package Araiguma.SpyCat.dtos;

import java.time.LocalDate;

import jakarta.validation.constraints.NotNull;

public record ImageInputDTO (
    Long id, 
    String url
) {
    
}
