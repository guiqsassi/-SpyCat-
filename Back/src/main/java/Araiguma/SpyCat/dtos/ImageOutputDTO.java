package Araiguma.SpyCat.dtos;


import Araiguma.SpyCat.Models.Image;
import jakarta.validation.constraints.NotNull;

public record ImageOutputDTO (
    @NotNull
    Long id, 
    @NotNull
    String url
){

public ImageOutputDTO(Image image){
    this(image.getId(), image.getUrl());
}
    
}
