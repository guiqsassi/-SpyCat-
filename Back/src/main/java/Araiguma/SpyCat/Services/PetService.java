package Araiguma.SpyCat.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import Araiguma.SpyCat.Enum.Status;
import Araiguma.SpyCat.Models.Pet;
import Araiguma.SpyCat.Repositories.PetRepository;
import Araiguma.SpyCat.dtos.PetInputDTO;
import Araiguma.SpyCat.dtos.PetOutputDTO;
import Araiguma.SpyCat.dtos.PetRescueInputDTO;
import Araiguma.SpyCat.dtos.PetSightingInputDTO;
import jakarta.transaction.Transactional;

@Service
public class PetService {
    @Autowired
    private PetRepository repository;

    @Transactional
    public PetOutputDTO create(PetInputDTO dto){
        Pet pet = new Pet(dto);
        return new PetOutputDTO(repository.save(pet));
    }

    @Transactional
    public PetOutputDTO update(PetInputDTO dto){
        if(repository.existsById(dto.id())){
            Pet pet = repository.findById(dto.id()).get();
            if (!dto.color().isEmpty()) {
                pet.setColor(dto.color());
            }
            if (!dto.specie().isEmpty()) {
                pet.setSpecie(dto.specie());
            }
            if (!dto.description().isEmpty()) {
                pet.setDescription(dto.description());
            }
            if (!dto.city().isEmpty()) {
                pet.setCity(dto.city());
            }
            if (!dto.state().isEmpty()) {
                pet.setState(dto.state());
            }

            PetOutputDTO resposta = new PetOutputDTO (repository.save(pet));
            return resposta;
        }
        else{
            return null;
        }
    } 
    @Transactional
    public PetOutputDTO rescue(PetRescueInputDTO pet){
        
        if(repository.existsById(pet.id())){
            Pet petAtualizar = repository.findById(pet.id()).get();

            petAtualizar.setStatus(pet.status());
            Pet resposta = repository.save(petAtualizar);

            return new PetOutputDTO(resposta);

        }
        return null;
    }

    public List<PetOutputDTO> list(Pageable page){
        // repository.findAll(Example.of(pet));
        List<PetOutputDTO> list = repository.findByStatusNotLike(Status.RESGATADO ,page).stream().map( pet -> new PetOutputDTO(pet)).toList();
        return list;
    }

    public PetOutputDTO read(Long id){
        if(repository.existsById(id)){
            return new PetOutputDTO(repository.findById(id).get()); 
        }
        else{
            return null;
        }
          }

public PetOutputDTO sighting(PetSightingInputDTO dto){
    if(repository.existsById(dto.id())){
        Pet pet = repository.findById(dto.id()).get();
        if (!dto.image().url().isEmpty()) {
            pet.setImages(dto.image());
        }
        pet.setLocation(dto.location());

        return new PetOutputDTO(repository.save(pet));
        
    }   
    else{
        return null;
    }
}

    public void delete(Long id){
        if(repository.existsById(id)){
            repository.deleteById(id);
        }
    }
}
