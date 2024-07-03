package Araiguma.SpyCat.LostPet;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import Araiguma.SpyCat.Models.LostPet;
import Araiguma.SpyCat.Models.User;
import Araiguma.SpyCat.Repositories.LostPetRepository;
import Araiguma.SpyCat.Services.LostPetService;
import Araiguma.SpyCat.dtos.LocationInputDTO;
import Araiguma.SpyCat.dtos.LostPetInputDTO;
import Araiguma.SpyCat.dtos.LostPetOutputDTO;


@SpringBootTest
public class LostPetServiceTest {
    @InjectMocks
    private LostPetService service;

    @Mock
    private LostPetRepository repositorie;

    @Test
    public void readNotFound() throws IOException{
        Long id = (long) 1;
        when(repositorie.existsById(id)).thenReturn(false);

        LostPetOutputDTO resultado = service.read(id);
        assertNull(resultado);
    }

    @Test
    public void readFound() throws IOException{
        Long id = (long) 1;

        LostPet lostPet = new LostPet();
        lostPet.setId(1l);
        var optional = Optional.of(lostPet);

        when(repositorie.existsById(id)).thenReturn(true);
        when(repositorie.findById(id)).thenReturn(optional);
        LostPetOutputDTO resultado = service.read(id);

        assertNotNull(resultado);
    }

    @Test
    public void SuccesfulLostPetCreate() throws IOException{

        LostPetInputDTO dto = new LostPetInputDTO(1l, "descrição", "cidade", "estado", "email@email.com", "phone", "cor", "especie", null);
        LostPet lostPet = new LostPet(dto);

        when(repositorie.save(lostPet)).thenReturn(lostPet);

        LostPetOutputDTO resultado = service.create(dto);

        assertNotNull(resultado);
        assertEquals(dto.id(), resultado.id());   
    }




    
    @Test
    public void SuccessfulLostPetUpdate() throws IOException{
        LocationInputDTO dtoLocation = new LocationInputDTO(1l, (double) 1, (double) 1, LocalDateTime.now());
        LostPetInputDTO dto = new LostPetInputDTO(1l, "descrição", "cidade", "estado", "email", "telefone", "cor", "especie", dtoLocation);
        LostPet lostPetUpdate = new LostPet(dto);


        when(repositorie.existsById(anyLong())).thenReturn(true);
        when(repositorie.save(any())).thenReturn(lostPetUpdate);

        LostPetOutputDTO resultado = service.update(dto);

        assertNotNull(resultado);
        assertEquals(lostPetUpdate.getId(), resultado.id());

    }

    @Test
    public void ErrorLostPetUpdate(){
        User user = new User();
        user.setId(1l);
        LocationInputDTO dtoLocation = new LocationInputDTO(1l, (double) 1, (double) 1, LocalDateTime.now());
        LostPetInputDTO dto = new LostPetInputDTO((long) 0, "amarelo", null, null, null, null, null, null, dtoLocation);

        when(repositorie.existsById(anyLong())).thenReturn(false);

        LostPetOutputDTO resultado = service.update(dto);

        assertNull(resultado);
    }
    
    @Test
    public void SuccessfulLostPetDelete() throws IOException{
        LostPet lostPetDelete = new LostPet();
        lostPetDelete.setId(1l);

        when(repositorie.existsById(anyLong())).thenReturn(true);

        service.delete(lostPetDelete.getId());

        verify(repositorie, times(1)).deleteById(1l);
    }

    @Test 
    public void ErrorLostPetDelete() throws IOException{
        LostPet lostPetDelete = new LostPet();
        lostPetDelete.setId(1l);

        when(repositorie.existsById(anyLong())).thenReturn(false);

        service.delete(lostPetDelete.getId());

        verify(repositorie, times(0)).deleteById(1l);
    }
}
