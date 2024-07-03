package Araiguma.SpyCat.dtos;

import java.time.LocalDateTime;

import Araiguma.SpyCat.Models.Pet;
import Araiguma.SpyCat.Models.User;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CommentInputDTO(
    Long id,
    @NotNull
    User user,
    @NotBlank
    String text,
    @NotNull
    LocalDateTime date,
    Pet pet
) {
    
}
