package Araiguma.SpyCat.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Araiguma.SpyCat.Models.Location;
import Araiguma.SpyCat.Repositories.LocationRepository;
import Araiguma.SpyCat.dtos.LocationInputDTO;
import Araiguma.SpyCat.dtos.LocationOutputDTO;
import jakarta.transaction.Transactional;

@Service
public class LocationService {
    @Autowired

    private LocationRepository repository;

    @Transactional
    public LocationOutputDTO create(LocationInputDTO dto){
        Location location = new Location(dto);
        repository.save(location);
        LocationOutputDTO locatioAuxiliar = new LocationOutputDTO(location);
        return locatioAuxiliar;
    }

    
    public List<LocationOutputDTO> list(){
        List<LocationOutputDTO> list = repository.findAll().stream().map(location -> new LocationOutputDTO(location)).toList();
        return list;
    }

    public LocationOutputDTO read(Long id){
        if(repository.existsById(id)){
            return new LocationOutputDTO(repository.findById(id).get()); 
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
