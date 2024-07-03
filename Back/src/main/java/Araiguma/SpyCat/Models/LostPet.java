package Araiguma.SpyCat.Models;

import Araiguma.SpyCat.dtos.LostPetInputDTO;
import jakarta.persistence.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class LostPet extends Pet {
    private String phone;
    private String email;
    
    
    public LostPet(LostPetInputDTO dto){
        this.id = dto.id();
        this.color = dto.color();
        this.specie = dto.specie();
        this.description = dto.description();
        this.city = dto.city();
        this.state = dto.state();
        this.locations.add(new Location(dto.location()));
        this.user = dto.user();
        this.status = dto.status();
        this.images = dto.images().stream().map(image-> new Image(image)).toList();

        this.email = dto.email();
        this.phone = dto.phone();
    }

  
}
