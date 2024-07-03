package Araiguma.SpyCat.Models;

import java.time.LocalDateTime;

import Araiguma.SpyCat.dtos.CommentInputDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor

public class Comment {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    private User user;
    
    private String text;
    private LocalDateTime date;

    @ManyToOne(optional = false)
    private Pet pet;


    public Comment(CommentInputDTO dto){
        this.id = dto.id();
        this.user = dto.user();
        this.text = dto.text();
        this.date = dto.date();
        this.pet = dto.pet();
    }
    
}
