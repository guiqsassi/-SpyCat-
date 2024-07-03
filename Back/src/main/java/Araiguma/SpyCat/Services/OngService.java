package Araiguma.SpyCat.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Araiguma.SpyCat.Models.Ong;
import Araiguma.SpyCat.Repositories.OngRepository;
import Araiguma.SpyCat.dtos.OngInputDTO;
import Araiguma.SpyCat.dtos.OngOutputDTO;
import jakarta.transaction.Transactional;

@Service
public class OngService {
    @Autowired

    private OngRepository repository;
    
    @Transactional
    public OngOutputDTO create(OngInputDTO dto){
        Ong ong = new Ong(dto);
        repository.save(ong);
        OngOutputDTO ongAuxiliar = new OngOutputDTO(ong);
        return ongAuxiliar;

    }
    
    public List<Ong> list(){
        List<Ong> list = (List<Ong>) repository.findAll();
        return list;
    }

    public OngOutputDTO read(Long id){
        if(repository.existsById(id)){
            return new OngOutputDTO(repository.findById(id).get());
        }
        else{
            return null;
        }
          }

}
