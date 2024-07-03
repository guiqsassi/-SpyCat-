package Araiguma.SpyCat.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import Araiguma.SpyCat.Models.Location;

public interface LocationRepository extends JpaRepository<Location, Long>{
    
}
