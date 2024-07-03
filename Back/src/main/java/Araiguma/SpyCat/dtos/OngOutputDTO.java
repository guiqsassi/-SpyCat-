package Araiguma.SpyCat.dtos;

import Araiguma.SpyCat.Models.Ong;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record OngOutputDTO(
    @NotNull
    Long id,
    @NotBlank
    String tradingName,
    @NotBlank
    String cnpj,
    @NotBlank
    String phone,
    @NotBlank
    @Email
    String email,
    LocationOutputDTO location
)
 {
    public OngOutputDTO(Ong ong){
        this(ong.getId(), ong.getTradingName(),ong.getCnpj(), ong.getPhone(), ong.getEmail(), new LocationOutputDTO(ong.getLocation()));
    }
 }
