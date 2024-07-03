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

import Araiguma.SpyCat.Models.Location;
import Araiguma.SpyCat.Services.LocationService;
import Araiguma.SpyCat.dtos.LocationInputDTO;
import Araiguma.SpyCat.dtos.LocationOutputDTO;
import jakarta.validation.Valid;


@RestController
@RequestMapping("/locations")
public class LocationController {
    @Autowired

    private LocationService service;
    
    @PostMapping
    public ResponseEntity<LocationOutputDTO> create(@Valid @RequestBody LocationInputDTO location){
        LocationOutputDTO LocationCriado = service.create(location);
        return new ResponseEntity<LocationOutputDTO>(LocationCriado, HttpStatus.CREATED);
    }
    @GetMapping("/{id}")
        public ResponseEntity<LocationOutputDTO> read (@PathVariable Long id){
            LocationOutputDTO locationBuscado = service.read(id);
            return new ResponseEntity<LocationOutputDTO>(locationBuscado,HttpStatus.OK);
    }
    @GetMapping
        public ResponseEntity<List<LocationOutputDTO>> list(){
            List<LocationOutputDTO> listLocations = service.list();
            return new ResponseEntity<List<LocationOutputDTO>>(listLocations, HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
        public ResponseEntity<Location> delete( @PathVariable Long id){
            service.delete(id);
            return new ResponseEntity<Location>(HttpStatus.NO_CONTENT);
        }
}
