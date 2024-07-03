package Araiguma.SpyCat.dtos;

import java.time.LocalDateTime;

import Araiguma.SpyCat.Models.Location;
import jakarta.validation.constraints.NotNull;

public record LocationOutputDTO(
    @NotNull
    Long id,
    @NotNull
    double longitude,
    @NotNull
    double latitude,
    @NotNull
    LocalDateTime date
) {
    public LocationOutputDTO(Location location){
        this(location.getId(), location.getLongitude(), location.getLatitude(), location.getDate());
    }
}
