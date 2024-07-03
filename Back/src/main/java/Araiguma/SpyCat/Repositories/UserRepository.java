package Araiguma.SpyCat.Repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import Araiguma.SpyCat.Models.User;

public interface UserRepository extends JpaRepository<User, Long>{
    User findByEmail(String email);
    User findByPasswordResetToken_token(String token);
}
