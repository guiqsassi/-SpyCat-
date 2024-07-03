package Araiguma.SpyCat.Repositories;
import org.springframework.data.jpa.repository.JpaRepository;

import Araiguma.SpyCat.Models.LostPet;

public interface LostPetRepository extends JpaRepository<LostPet, Long> {
    
}
