package Araiguma.SpyCat.dtos;

import Araiguma.SpyCat.Models.PasswordResetToken;

public record PasswordResetTokenDto(
    Long id,
    String token
) {
    public PasswordResetTokenDto(PasswordResetToken token){
        this(token.getId(), token.getToken());
    }   
}
