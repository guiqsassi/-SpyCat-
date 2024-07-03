package Araiguma.SpyCat.Controllers;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Araiguma.SpyCat.Models.Pet;
import Araiguma.SpyCat.Services.PetService;
import Araiguma.SpyCat.dtos.PetInputDTO;
import Araiguma.SpyCat.dtos.PetOutputDTO;
import Araiguma.SpyCat.dtos.PetRescueInputDTO;
import Araiguma.SpyCat.dtos.PetSightingInputDTO;
import jakarta.validation.Valid;


@RestController
@RequestMapping("/pets")
@CrossOrigin("*")
public class PetController {
    @Autowired

    private PetService service;
    
    @PostMapping
    public ResponseEntity<PetOutputDTO> create(@Valid @RequestBody PetInputDTO pet){
        PetOutputDTO PetCriado = service.create(pet);
        return new ResponseEntity<PetOutputDTO>(PetCriado, HttpStatus.CREATED);
    }
    @PostMapping("/rescue")
    public ResponseEntity<PetOutputDTO> rescue(@Valid @RequestBody PetRescueInputDTO pet){
        PetOutputDTO PetCriado = service.rescue(pet);
        return new ResponseEntity<PetOutputDTO>(PetCriado, HttpStatus.OK);
    }
    @PutMapping
        public ResponseEntity<PetOutputDTO> update(@Valid @RequestBody PetInputDTO pet){
        PetOutputDTO petUpdate = service.update(pet);
        return new ResponseEntity<>(petUpdate, HttpStatus.OK);
    }
    @GetMapping("/{id}")
        public ResponseEntity<PetOutputDTO> read (@PathVariable Long id){
            PetOutputDTO petBuscado = service.read(id);
            return new ResponseEntity<PetOutputDTO>(petBuscado,HttpStatus.OK);
    }
    @GetMapping
        public ResponseEntity<List<PetOutputDTO>> list( Pageable page){
            List<PetOutputDTO> listPets = service.list(page);
            return new ResponseEntity<List<PetOutputDTO>>(listPets, HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
        public ResponseEntity<Pet> delete( @PathVariable Long id){
            service.delete(id);
            return new ResponseEntity<Pet>(HttpStatus.NO_CONTENT);
        }
    @PostMapping("/sighting")
    public ResponseEntity<PetOutputDTO> sighting(@Valid @RequestBody PetSightingInputDTO pet){
        PetOutputDTO PetCriado = service.sighting(pet);
        return new ResponseEntity<PetOutputDTO>(PetCriado, HttpStatus.OK);
    }
}
