package Araiguma.SpyCat.Models;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import Araiguma.SpyCat.dtos.UserInputDTO;
import Araiguma.SpyCat.dtos.UserLoginInputDTO;
import Araiguma.SpyCat.dtos.UserUpdateInputDTO;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor
public class User implements UserDetails{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(unique = true, nullable = false)
    private String email;
    @Column(unique = true, nullable = false)
    private String username;
    @Column(nullable = false)
    private String password;
    // @Column(nullable = false)
    private String city;
    // @Column(nullable = false)
    private String state;
    @OneToMany
    private List<PasswordResetToken> passwordResetToken = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Pet> pets = new ArrayList<Pet>();

    @ManyToMany(cascade = CascadeType.ALL)
    private List<Pet> petsFavoritos = new ArrayList<Pet>(); 
    private String icon;

    public User (UserInputDTO dto){
        this.username = dto.username();
        this.password = dto.password();
        this.email = dto.email();

    }
    public void setPasswordResetToken(PasswordResetToken token){
        this.passwordResetToken.add(token);
    }


    public User (UserLoginInputDTO dto){
        this.email = dto.email();
        this.password = dto.password();
    }

    public User (UserUpdateInputDTO dto){
        this.id = dto.id();
        this.email = dto.email();
        this.username = dto.username();
        this.city = dto.city();
        this.password = dto.password();
        this.state = dto.state();
        this.icon = dto.icon();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }


    @Override
    public String getUsername(){
        return this.email;
    }
    
    public void setPetsFavoritos(Long id){
        Pet pet = new Pet();
        pet.setId(id);
        petsFavoritos.add(pet);
    }
    
    public String getUsernameReal(){
        return this.username;
    }

}
