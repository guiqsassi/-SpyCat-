package Araiguma.SpyCat.Controllers;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Araiguma.SpyCat.Models.LostPet;
import Araiguma.SpyCat.Services.LostPetService;
import Araiguma.SpyCat.dtos.LostPetInputDTO;
import Araiguma.SpyCat.dtos.LostPetOutputDTO;
import jakarta.validation.Valid;


@RestController
@RequestMapping("/lostPets")
public class LostPetController {
    @Autowired

    private LostPetService service;
    
    @PostMapping
    public ResponseEntity<LostPetOutputDTO> create(@Valid @RequestBody LostPetInputDTO lostPet){
        LostPetOutputDTO LostPetCriado = service.create(lostPet);
        return new ResponseEntity<LostPetOutputDTO>(LostPetCriado, HttpStatus.CREATED);
    }
    @PutMapping
        public ResponseEntity<LostPetOutputDTO> update(@Valid @RequestBody LostPetInputDTO lostPet){
        LostPetOutputDTO lostPetUpdate = service.update(lostPet);
        return new ResponseEntity<>(lostPetUpdate, HttpStatus.OK);
    }
    @GetMapping("/{id}")
        public ResponseEntity<LostPetOutputDTO> read (@PathVariable Long id){
            LostPetOutputDTO lostPetBuscado = service.read(id);
            return new ResponseEntity<LostPetOutputDTO>(lostPetBuscado,HttpStatus.OK);
    }
    @GetMapping
        public ResponseEntity<List<LostPet>> list(){
            List<LostPet> listLostPets = service.list();
            return new ResponseEntity<List<LostPet>>(listLostPets, HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
        public ResponseEntity<LostPet> delete( @PathVariable Long id){
            service.delete(id);
            return new ResponseEntity<LostPet>(HttpStatus.NO_CONTENT);
        }
}
