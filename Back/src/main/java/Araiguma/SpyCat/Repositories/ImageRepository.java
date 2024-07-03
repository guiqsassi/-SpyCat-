package Araiguma.SpyCat.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import Araiguma.SpyCat.Models.Image;


public interface ImageRepository extends JpaRepository<Image,Long> {
    
}
