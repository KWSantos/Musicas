package example.com.musics.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import example.com.musics.domain.model.Users;

public interface UserRepository extends JpaRepository <Users, Long> {
    
    Optional<Users>findByEmail(String email);
}
