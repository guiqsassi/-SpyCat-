package Araiguma.SpyCat.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Araiguma.SpyCat.Models.Image;
import Araiguma.SpyCat.Repositories.ImageRepository;
import Araiguma.SpyCat.dtos.ImageInputDTO;
import Araiguma.SpyCat.dtos.ImageOutputDTO;
import jakarta.transaction.Transactional;

@Service
public class ImageService {
        @Autowired
    private ImageRepository repository;

    @Transactional
    public ImageOutputDTO create(ImageInputDTO dto){
        Image image = new Image(dto);
        return new ImageOutputDTO(repository.save(image));
    }

    @Transactional
    public ImageOutputDTO update(ImageInputDTO image){
        Image imageUpdated = new Image(image);
        if(repository.existsById(imageUpdated.getId())){
            ImageOutputDTO resposta = new ImageOutputDTO (repository.save(imageUpdated));
            return resposta;
        }
        else{
            return null;
        }
    } 


    public List<ImageOutputDTO> list(){
        // repository.findAll(Example.of(image));
        List<ImageOutputDTO> list = repository.findAll().stream().map( image -> new ImageOutputDTO(image)).toList();
        return list;
    }

    public ImageOutputDTO read(Long id){
        if(repository.existsById(id)){
            return new ImageOutputDTO(repository.findById(id).get()); 
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
