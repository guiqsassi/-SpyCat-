package Araiguma.SpyCat.Config;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import Araiguma.SpyCat.Repositories.RefreshTokenRepository;
import Araiguma.SpyCat.Repositories.UserRepository;
import Araiguma.SpyCat.Services.TokenService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    @Autowired
    private TokenService service;

    @Autowired
    private UserRepository repository;

    @Autowired
    private RefreshTokenRepository tokenRepository;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain) throws IOException, ServletException {
        var tokenJWT = recuperarToken(request);

        if (tokenJWT != null) {
            var subject = service.getSubject(tokenJWT);
            tokenRepository.findByToken(tokenJWT).orElseThrow();
            var usuario = repository.findByEmail(subject);

            var authentication = new UsernamePasswordAuthenticationToken(
                    usuario, null, usuario.getAuthorities());

            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        filterChain.doFilter(request, response);
    }

    // Recupera o token a partir da request
    private String recuperarToken(HttpServletRequest request) {
        var authorizationHeader = request.getHeader("Authorization");
        if (authorizationHeader != null) {
            return authorizationHeader.replace("Bearer ", "");
        }
        return null;
    }

}
