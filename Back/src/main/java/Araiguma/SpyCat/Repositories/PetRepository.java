package Araiguma.SpyCat.Repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import Araiguma.SpyCat.Enum.Status;
import Araiguma.SpyCat.Models.Pet;

public interface PetRepository extends JpaRepository<Pet, Long> {
    public Page<Pet> findByStatusNotLike(Status status, Pageable pageable);

}
