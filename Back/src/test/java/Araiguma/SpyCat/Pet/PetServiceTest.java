package Araiguma.SpyCat.Pet;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import Araiguma.SpyCat.Enum.Status;
import Araiguma.SpyCat.Models.Location;
import Araiguma.SpyCat.Models.Pet;
import Araiguma.SpyCat.Models.User;
import Araiguma.SpyCat.Repositories.PetRepository;
import Araiguma.SpyCat.Services.PetService;
import Araiguma.SpyCat.dtos.LocationInputDTO;
import Araiguma.SpyCat.dtos.PetInputDTO;
import Araiguma.SpyCat.dtos.PetOutputDTO;
import Araiguma.SpyCat.dtos.PetRescueInputDTO;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class PetServiceTest {
    
    @InjectMocks
    public PetService service;

    @Mock
    public PetRepository repository;

    @Test
    public void SuccesfulPetCreate(){
        User user = new User();
        user.setId(1);
        user.setUsername("Caio");
        LocationInputDTO dtoLocation = new LocationInputDTO(1l, 1d, 1d, LocalDateTime.now());
        PetInputDTO dtoPet = new PetInputDTO(0l, null, null, null, null, null, null, null, dtoLocation, user);
        Pet pet = new Pet(dtoPet);
        when(repository.save(pet)).thenReturn(pet);

        PetOutputDTO resultado = service.create(dtoPet);
        assertNotNull(resultado);
    }

    @Test
    public void SuccessfulPetUpdate(){
    User user = new User();
    user.setId(1);   
    LocationInputDTO dtoLocation = new LocationInputDTO(1l, (double) 1, (double) 1, LocalDateTime.now());
    PetInputDTO dto = new PetInputDTO((long) 0, "amarelo", null, null, null, null, null, null, dtoLocation, user);

     Pet pet = new Pet(dto);
     pet.setId(1l);//pq no constructor n tem id então tem que colcoar 1 pra esse teste (na aula do marcola)
     pet.setColor("Laranja");
     when(repository.existsById(anyLong())).thenReturn(true);
     when(repository.save(any())).thenReturn(pet);
     
    PetOutputDTO petAtualizado = service.update(dto);

    assertEquals(pet.getId(), petAtualizado.id());
    assertNotNull(petAtualizado);
    }
    
    @Test
    public void ErrorPetUpdate(){
        User user = new User();
        user.setId(1);   
        LocationInputDTO dtoLocation = new LocationInputDTO(1l,(double) 1, (double) 1, LocalDateTime.now());
        PetInputDTO dto = new PetInputDTO((long) 0, "amarelo", null, null, null, null, null, null, dtoLocation, user);

     when(repository.existsById(anyLong())).thenReturn(false);
     
    PetOutputDTO petAtualizado = service.update(dto);

    assertNull(petAtualizado);
    }
    
    
    @Test
    public void readFoundPet() throws IOException{
        Long id = (long) 1;
        User user = new User();
        user.setId(1);   

        Pet pet = new Pet();
        pet.setUser(user);
        pet.setColor("Amarelo");


        var optional = Optional.of(pet);

        when(repository.existsById(anyLong())).thenReturn(true);

        when(repository.findById(anyLong())).thenReturn(optional);
        
        PetOutputDTO resultado = service.read(id);


        assertNotNull(resultado);
        assertEquals(resultado.color(), pet.getColor());
    }

    @Test
    public void readNotFoundPet() throws IOException{
        Long id = (long) 1;

        when(repository.existsById(anyLong())).thenReturn(false);

        
        PetOutputDTO resultado = service.read(id);


        assertNull(resultado);
    }
    @Test 
    public void SuccesfulDeletePet() throws IOException{

        Pet petDelete = new Pet();
        petDelete.setId(1l);

        when(repository.existsById(anyLong())).thenReturn(true);

        service.delete(petDelete.getId());

        verify(repository, times(1)).deleteById(1l);
    }
    @Test 
    public void ErrorDeletePet() throws IOException{

        Pet petDelete = new Pet();
        petDelete.setId(1l);

        when(repository.existsById(anyLong())).thenReturn(false);

        service.delete(petDelete.getId());

        verify(repository, times(0)).deleteById(1l);
    }

    @Test
    public void listPet(){
        Page<Pet> listaPets;
        Pet pet = new Pet();
        User user = new User();
        user.setId(1l);
        pet.setColor("azul");
        pet.setUser(user);
        // listaPets.(pet);
        
        // when(repository.findByStatusNotLike(Status.RESGATADO, any(Pageable.class))).thenReturn(listaPets);

        // List<PetOutputDTO> resposta = service.list();

        // assertNotNull(resposta);
    }

    @Test
    public void conversaoOutputDTO(){
        Pet pet = new Pet();
        pet.setColor("Aracaju");
        User user = new User();
        user.setId(1l);

        pet.setUser(user);
        PetOutputDTO dto = new PetOutputDTO(pet);

        assertEquals(pet.getColor(), dto.color());

    }
    @Test
    public void conversaoInputDTO(){
        User user = new User();
        user.setId(1l);

        LocationInputDTO location = new LocationInputDTO(1l, 1d, 1d, LocalDateTime.now()); 

        PetInputDTO dto = new PetInputDTO(0l, "azul", null, null, null, null, null, null, location, user);

        Pet pet = new Pet(dto);



        assertEquals(pet.getColor(), dto.color());

    }
    @Test
    public void rescuePet(){
        User user = new User();
        user.setId(1l);

        LocationInputDTO location = new LocationInputDTO(1l,1d, 1d, LocalDateTime.now()); 


    }

}
