package Araiguma.SpyCat.Models;

import java.util.ArrayList;
import java.util.List;

import Araiguma.SpyCat.Enum.Status;
import Araiguma.SpyCat.dtos.ImageInputDTO;
import Araiguma.SpyCat.dtos.LocationInputDTO;
import Araiguma.SpyCat.dtos.PetInputDTO;
import Araiguma.SpyCat.dtos.PetSightingInputDTO;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Pet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected long id;
    protected String color;
    protected String specie;
    protected String description;
    protected String city;
    protected String state;
    @Enumerated(EnumType.STRING)
    protected Status status;

    @OneToMany(cascade = CascadeType.ALL)
    protected List<Location> locations = new ArrayList<Location>();
   
    @OneToMany(cascade = CascadeType.ALL)
    protected List<Image> images = new ArrayList<Image>();

    @ManyToOne
    @JoinColumn(name ="user_id")
    protected User user;

    @OneToMany(mappedBy = "pet", cascade = CascadeType.ALL)
    protected List<Comment> comments = new ArrayList<Comment>();




    
    public Pet(PetInputDTO dto){
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
        // this.images.add(new Image(dto.images()));
    }
    public Pet(PetSightingInputDTO dto){
        this.id = dto.id();
        this.locations.add(new Location(dto.location()));
        this.images.add(new Image(dto.image()));

    }

    
    public void setImages(ImageInputDTO image){
        this.images.add(new Image(image));
    }
    public void setLocation(LocationInputDTO location){
        this.locations.add(new Location(location));
    }
}
