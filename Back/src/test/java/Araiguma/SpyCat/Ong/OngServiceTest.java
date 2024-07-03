package Araiguma.SpyCat.Ong;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;

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

import Araiguma.SpyCat.Models.Location;
import Araiguma.SpyCat.Models.Ong;
import Araiguma.SpyCat.Repositories.OngRepository;
import Araiguma.SpyCat.Services.OngService;
import Araiguma.SpyCat.dtos.LocationInputDTO;
import Araiguma.SpyCat.dtos.OngInputDTO;
import Araiguma.SpyCat.dtos.OngOutputDTO;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class OngServiceTest {
    
    @InjectMocks
    private OngService service;

    @Mock
    private OngRepository repository;

    @Test
    public void SuccessfullycreateOng() throws IOException{
        
        LocationInputDTO dtoLocation = new LocationInputDTO(1l,1d, 2d, LocalDateTime.now());
        OngInputDTO dto = new OngInputDTO(1l, "trandingName", "cnpj", "phone", "email", dtoLocation );

        Ong ong = new Ong(dto);

        when(repository.save(ong)).thenReturn(ong);

        OngOutputDTO result = service.create(dto);

        assertNotNull(result);
        assertEquals(dto.email(), result.email());
    }

    @Test
    public void readOngFond() throws IOException{

        Long id = 1l;

        Ong ong = new Ong();
        LocationInputDTO locationDto = new LocationInputDTO(1l, 0, 0, null);
        var optional = Optional.of(ong);
        ong.setLocation(new Location(locationDto));

        when(repository.existsById(id)).thenReturn(true);
        when(repository.findById(id)).thenReturn(optional);

        OngOutputDTO result = service.read(id);

        assertNotNull(result);
    }

    @Test
    public void readNotFound() throws IOException{
        Long id = 1l;
        when(repository.existsById(id)).thenReturn(false);

        OngOutputDTO result = service.read(id);
        assertNull(result);
    }

    @Test
    public void readList() throws IOException{

        List<Ong> listaOngs = new ArrayList<Ong>();

        LocationInputDTO dtoLocation = new LocationInputDTO(1l, 1d, 2d, LocalDateTime.now());
        OngInputDTO dtoOng = new OngInputDTO(1l, "tradingName", "cnpj", "phone", "email", dtoLocation);
        Ong ong = new Ong(dtoOng);

        listaOngs.add(ong);

        when(repository.findAll()).thenReturn(listaOngs);

        List<Ong> result = service.list();
        assertNotNull(result);
    }

    @Test
    public void conversaoInputDTO() throws IOException{
        LocationInputDTO dtoLocation = new LocationInputDTO(1l, 1d, 2d, LocalDateTime.now());
        OngInputDTO dtoOng = new OngInputDTO(1l, "tradingName", "cnpj", "phone", "email", dtoLocation);
        
        Ong ong = new Ong(dtoOng);

        assertEquals(dtoOng.email() , ong.getEmail());
        
    }

    @Test
    public void conversaoOutputDTO() throws IOException{
        LocationInputDTO dtoLocation = new LocationInputDTO(1l,1d, 2d, LocalDateTime.now());
        OngInputDTO dtoOng = new OngInputDTO(1l, "tradingName", "cnpj", "phone", "email", dtoLocation);
        
        Ong ong = new Ong(dtoOng);

        assertEquals(dtoOng.email() , ong.getEmail());
    }


}


