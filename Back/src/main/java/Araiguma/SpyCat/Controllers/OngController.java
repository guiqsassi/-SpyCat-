package Araiguma.SpyCat.Controllers;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

import Araiguma.SpyCat.Models.Ong;
import Araiguma.SpyCat.Services.OngService;
import Araiguma.SpyCat.dtos.OngInputDTO;
import Araiguma.SpyCat.dtos.OngOutputDTO;
import jakarta.validation.Valid;


@RestController
@RequestMapping("/ongs")
@CrossOrigin("*")
public class OngController {
    @Autowired

    private OngService service;
    
    @PostMapping
    public ResponseEntity<OngOutputDTO> create(@Valid @RequestBody OngInputDTO ong){
        OngOutputDTO OngCriado = service.create(ong);
        return new ResponseEntity<OngOutputDTO>(OngCriado, HttpStatus.CREATED);
    }
    @GetMapping("/{id}")
        public ResponseEntity<OngOutputDTO> read (@Valid @PathVariable Long id){
            OngOutputDTO ongBuscado = service.read(id);
            return new ResponseEntity<OngOutputDTO>(ongBuscado,HttpStatus.OK);
    }
    @GetMapping
        public ResponseEntity<List<Ong>> list(){
            List<Ong> listOngs = service.list();
            return new ResponseEntity<List<Ong>>(listOngs, HttpStatus.OK);
    }

}
