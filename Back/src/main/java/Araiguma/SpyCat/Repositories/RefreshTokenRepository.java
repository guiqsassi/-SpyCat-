package Araiguma.SpyCat.Repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

import Araiguma.SpyCat.Models.RefreshToken;
import Araiguma.SpyCat.Models.User;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
   Optional<RefreshToken> findByToken(String token);
   Optional<RefreshToken> findByRefreshToken(String refreshToken);
   @Modifying
   int deleteByUser(User user);
}
