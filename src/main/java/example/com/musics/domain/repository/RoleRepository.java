package example.com.musics.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import example.com.musics.domain.model.Role;
import example.com.musics.domain.Enum.ETypeRole;

public interface RoleRepository extends JpaRepository <Role, Long>{

    Optional<Role> findByName(ETypeRole name);
}  
