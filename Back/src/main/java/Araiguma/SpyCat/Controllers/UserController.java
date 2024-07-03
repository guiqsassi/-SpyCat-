package Araiguma.SpyCat.Controllers;


import java.util.List;
import java.util.Optional;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import Araiguma.SpyCat.Models.User;
import Araiguma.SpyCat.Services.UserService;
import Araiguma.SpyCat.dtos.UserFavoritePetInputDTO;
import Araiguma.SpyCat.dtos.UserInputDTO;
import Araiguma.SpyCat.dtos.UserLoginInputDTO;
import Araiguma.SpyCat.dtos.UserOutputDTO;
import Araiguma.SpyCat.dtos.UserUpdateInputDTO;
import jakarta.validation.Valid;


@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired

    private UserService service;
    
    @PostMapping
    public ResponseEntity<UserOutputDTO> create(@Valid @RequestBody UserInputDTO user){
        UserOutputDTO UserCriado = service.create(user);
        return new ResponseEntity<UserOutputDTO>(UserCriado, HttpStatus.CREATED);
    }
    
    @PutMapping
        public ResponseEntity<UserOutputDTO> update(@Valid @RequestBody UserUpdateInputDTO user){
        UserOutputDTO userUpdate = service.update(user);
        return new ResponseEntity<>(userUpdate, HttpStatus.OK);
    }
    @GetMapping("/{id}")
        public ResponseEntity<UserOutputDTO> read (@Valid @PathVariable Long id){
            UserOutputDTO userBuscado = service.read(id);
            return new ResponseEntity<UserOutputDTO>(userBuscado,HttpStatus.OK);
    }
    @GetMapping
        public ResponseEntity<List<UserOutputDTO>> list(){
            List<UserOutputDTO> listUsers = service.list();
            return new ResponseEntity<List<UserOutputDTO>>(listUsers, HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
        public ResponseEntity<User> delete( @PathVariable Long id, @RequestParam String password){
            service.delete(id, password);
            return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
        }
    @PostMapping("/favorite")
    public ResponseEntity<UserOutputDTO> favorite( @RequestBody UserFavoritePetInputDTO dto){
        UserOutputDTO user = service.favoritarPet(dto);
        return new ResponseEntity<UserOutputDTO>(user, HttpStatus.OK);
    }
}
