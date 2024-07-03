package Araiguma.SpyCat.Models;

import Araiguma.SpyCat.dtos.ImageInputDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String url;
    
    @ManyToOne
    @JoinColumn(name = "pet_id")
    private Pet pet;

    public Image(ImageInputDTO dto){
        this.id = dto.id();
        this.url = dto.url();
        
    }
}
