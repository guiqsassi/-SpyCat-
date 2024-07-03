package Araiguma.SpyCat.Services;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import Araiguma.SpyCat.Models.PasswordResetToken;
import Araiguma.SpyCat.Models.User;
import Araiguma.SpyCat.Repositories.CommentRepository;
import Araiguma.SpyCat.Repositories.PasswordResetTokenRepository;
import Araiguma.SpyCat.Repositories.UserRepository;
import Araiguma.SpyCat.dtos.UserFavoritePetInputDTO;
import Araiguma.SpyCat.dtos.UserInputDTO;
import Araiguma.SpyCat.dtos.UserOutputDTO;
import Araiguma.SpyCat.dtos.UserUpdateInputDTO;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepository repository;
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private PasswordResetTokenRepository passwordResetTokenRepository;
    
    @Transactional
    public UserOutputDTO create(UserInputDTO dto){
        String password = new BCryptPasswordEncoder().encode(dto.password());
        User user = new User(dto);
        user.setPassword(password);
        repository.save(user);
        UserOutputDTO userAuxiliar = new UserOutputDTO(user);
        return userAuxiliar;
    }

    @Transactional
    public UserOutputDTO update(UserUpdateInputDTO user){

        if(repository.existsById(user.id())){

            User userUpdate = repository.findById(user.id()).get();
            if(user.email() != null && !user.email().isEmpty()){
                userUpdate.setEmail(user.email());
            }
            if(user.username() != null&& !user.username().isEmpty()){
                userUpdate.setUsername(user.username());
            }
            if(user.city() != null && !user.city().isEmpty()){
                userUpdate.setCity(user.city());
            }
            if(user.state() != null && !user.state().isEmpty()){
                userUpdate.setState(user.state());
            }
            if(user.icon() != null&& !user.icon().isEmpty()){
                userUpdate.setIcon(user.icon());
            }
            if(user.password() != null&& !user.password().isEmpty()){
                String password = new BCryptPasswordEncoder().encode(user.password());
                userUpdate.setPassword(password);
            }
            return new UserOutputDTO( repository.save(userUpdate));
            
        }
        else{
            return null;
        }
    } 
    
    public List<UserOutputDTO> list(){
        List<UserOutputDTO> list = repository.findAll().stream().map(user -> new UserOutputDTO(user)).toList();
        return list;
    }

    public UserOutputDTO read(Long id){
        if(repository.existsById(id)){
            return new UserOutputDTO(repository.findById(id).get());
        }
        else{
            return null;
        }
      
    }
    public User findUserBypasswordResetToken(String token){
        return repository.findByPasswordResetToken_token(token);
    }

    public void delete(Long id, String password){
        if(repository.existsById(id)){
       
            
            User user = repository.findById(id).get();
            if(user.getPassword().equals(password)){
                
                repository.deleteById(id);

            }
        }
    }
    public void changeUserPassword(User user, String password) {
        String passwordEncriptada = new BCryptPasswordEncoder().encode(password);
        user.setPassword(passwordEncriptada);
        repository.save(user);
    }
    
        public void createPasswordResetTokenForUser(User user, String token) {
        PasswordResetToken myToken = new PasswordResetToken();
        myToken.setUser(user);
        myToken.setToken(token);
        myToken.setExpiryDate(LocalDateTime.now()
        .plusMinutes(5l)
        .toInstant(ZoneOffset.of("-03:00")));
        user.setPasswordResetToken(passwordResetTokenRepository.save(myToken));
        repository.save(user);
    

}
    public UserOutputDTO favoritarPet(UserFavoritePetInputDTO dto){
        if(repository.existsById(dto.id_user())){

            User user = repository.findById(dto.id_user()).get();
            user.setPetsFavoritos(dto.id_pet());
            
            return new UserOutputDTO(repository.save(user));

        }
        else{
            return null;
        }
        

    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = repository.findByEmail(email);
        if(user != null){
            return org.springframework.security.core.userdetails.User.builder()
                .password(user.getPassword())
                .username(user.getUsername())
            .build();
        }else{
            throw new UsernameNotFoundException("");
        }
    }
 
    
}
