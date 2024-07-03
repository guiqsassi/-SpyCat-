package Araiguma.SpyCat.Models;

import Araiguma.SpyCat.dtos.OngInputDTO;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor
public class Ong {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ong_id")
    private long id;
    @Column(nullable = false, unique = true)
    private String tradingName;
    @Column( nullable = false, unique = true)
    private String cnpj;
    @Column(nullable = false, length = 11)
    private String phone;
    @Column(nullable = false, unique = true )
    private String email;
    @OneToOne(cascade =  CascadeType.ALL, optional = false)
    private Location location;

    public Ong(OngInputDTO dto){
        this.tradingName = dto.tradingName();
        this.cnpj = dto.cnpj();
        this.phone = dto.phone();
        this.email = dto.email();
        this.location = new Location(dto.location());
    }

}
