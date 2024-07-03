package Araiguma.SpyCat.dtos;

import org.springframework.validation.FieldError;

public record ErroDTO (
    String field, 
    String error
) {
   
    public ErroDTO(FieldError error){
        this(error.getField(), error.getDefaultMessage());
   }
}
