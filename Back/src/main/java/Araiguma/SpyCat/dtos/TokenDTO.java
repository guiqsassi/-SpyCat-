package Araiguma.SpyCat.dtos;

public record TokenDTO(
    String token,
    String refreshToken,
    String email,
    Long id,
    String icon
) {
}
