package Araiguma.SpyCat.Controllers;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import Araiguma.SpyCat.Models.RefreshToken;
import Araiguma.SpyCat.Models.User;
import Araiguma.SpyCat.Repositories.UserRepository;
import Araiguma.SpyCat.Services.EmailSenderService;
import Araiguma.SpyCat.Services.RefreshTokenService;
import Araiguma.SpyCat.Services.TokenService;
import Araiguma.SpyCat.Services.UserService;
import Araiguma.SpyCat.dtos.PasswordResetDTO;
import Araiguma.SpyCat.dtos.RefreshTokenDTO;
import Araiguma.SpyCat.dtos.TokenDTO;
import Araiguma.SpyCat.dtos.UserLoginInputDTO;
import Araiguma.SpyCat.dtos.UserOutputDTO;
import jakarta.validation.Valid;

@RestController
public class LoginController {
    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private RefreshTokenService refreshTokenService;

    @Autowired
    private TokenService tokenService;
    @Autowired
    private UserRepository repository;
    @Autowired
    private EmailSenderService emailSenderService;
    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<TokenDTO> login(@RequestBody @Valid UserLoginInputDTO dados) {
        var authenticationToken = new UsernamePasswordAuthenticationToken(
                dados.email(),
                dados.password());
        var authentication = manager.authenticate(authenticationToken);
        var token = tokenService.gerarToken((UserDetails) authentication.getPrincipal());
        var user = (UserDetails) authentication.getPrincipal();
        var refreshToken = refreshTokenService.createRefreshToken(user, token);
        var UserAchado = repository.findByEmail(dados.email());
        return new ResponseEntity<TokenDTO>(
                new TokenDTO(token, refreshToken.getRefreshToken(), user.getUsername(), UserAchado.getId(), UserAchado.getIcon()),
                HttpStatus.CREATED);
    }

    @PostMapping("/refresh")
    public ResponseEntity<TokenDTO> refreshtoken(@Valid @RequestBody RefreshTokenDTO request) {
        String requestRefreshToken = request.refreshToken();
        return refreshTokenService.findByToken(requestRefreshToken)
                .map(refreshTokenService::verifyExpiration)
                .map(RefreshToken::getUser)
                .map(user -> getToken(requestRefreshToken, user))
                .orElseThrow(() -> new RuntimeException("Refresh token is not in database!"));
    }

    private ResponseEntity<TokenDTO> getToken(String requestRefreshToken, User usuario) {
        String token = tokenService.gerarToken(usuario);
        refreshTokenService.deleteByUser(usuario);
        RefreshToken refreshToken = refreshTokenService.createRefreshToken(usuario, token);
        return ResponseEntity.ok(new TokenDTO(token, refreshToken.getRefreshToken(), usuario.getEmail(), usuario.getId(), usuario.getIcon()));
    }

    @DeleteMapping("/revoke")
    public ResponseEntity<?> revokeToken() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        var usuario = (User) auth.getPrincipal();
        refreshTokenService.deleteByUser(usuario);
        return ResponseEntity.noContent().build();
    }

        @PostMapping("/forgot-password")
    public ResponseEntity<String> forgotPassword(@RequestParam String email){
        User user = repository.findByEmail(email);
        if(!user.getEmail().isEmpty()){

            String token = UUID.randomUUID().toString();
    userService.createPasswordResetTokenForUser(user, token);
    
            String conteudo = new String("Para resetar a sua senha, é necessário clicar neste link: http://localhost:3000/ChangePassword?token=" + token);
            
            emailSenderService.enviar(user.getEmail(), "Senha esquecida", conteudo);
            return new ResponseEntity<>(token,HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    
}
        @PostMapping("/savePassword")
    public ResponseEntity<UserOutputDTO> savePassword( @RequestBody PasswordResetDTO dto){
        User user = userService.findUserBypasswordResetToken(dto.token());

        if(!user.getEmail().isEmpty()) {
            if(user.getPasswordResetToken().get(user.getPasswordResetToken().size()-1).getExpiryDate().isAfter(Instant.now())){
                userService.changeUserPassword(user, dto.password());
                return new ResponseEntity<UserOutputDTO>(new UserOutputDTO(user), HttpStatus.OK);

            }
        }
        else{
            return new ResponseEntity<UserOutputDTO>(HttpStatus.NOT_MODIFIED);
        }
        return null;
    }
    

}
