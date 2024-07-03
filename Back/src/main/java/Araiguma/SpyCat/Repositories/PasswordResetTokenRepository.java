package Araiguma.SpyCat.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import Araiguma.SpyCat.Models.PasswordResetToken;

public interface PasswordResetTokenRepository extends JpaRepository<PasswordResetToken, Long>{
    
}
