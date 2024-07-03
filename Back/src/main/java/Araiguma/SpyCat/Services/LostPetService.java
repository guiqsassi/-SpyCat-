package Araiguma.SpyCat.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Araiguma.SpyCat.Models.LostPet;
import Araiguma.SpyCat.Repositories.LostPetRepository;
import Araiguma.SpyCat.dtos.LostPetInputDTO;
import Araiguma.SpyCat.dtos.LostPetOutputDTO;
import jakarta.transaction.Transactional;

@Service
public class LostPetService {
    @Autowired

    private LostPetRepository repository;

    @Transactional
    public LostPetOutputDTO create(LostPetInputDTO dto){
        LostPet lostPet = new LostPet(dto);
        repository.save(lostPet);
        LostPetOutputDTO lostPetAuxiliar = new LostPetOutputDTO(lostPet);
        return lostPetAuxiliar;
    }

    @Transactional
    public LostPetOutputDTO update(LostPetInputDTO dto){
        LostPet lostPetUpdated = new LostPet(dto);
        if(repository.existsById(lostPetUpdated.getId())){
            LostPetOutputDTO resposta = new LostPetOutputDTO(repository.save(lostPetUpdated));
            return resposta;
            
        }
        else{
            return null;
        }
    } 
    
    public List<LostPet> list(){
        List<LostPet> list = (List<LostPet>) repository.findAll();
        return list;
    }

    public LostPetOutputDTO read(Long id){
        if(repository.existsById(id)){
            return new LostPetOutputDTO(repository.findById(id).get());
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
