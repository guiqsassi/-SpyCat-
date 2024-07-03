package Araiguma.SpyCat.dtos;

import jakarta.validation.constraints.NotBlank;

public record RefreshTokenDTO(
    @NotBlank
    String refreshToken
) {
}
