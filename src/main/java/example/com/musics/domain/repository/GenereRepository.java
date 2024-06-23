package example.com.musics.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import example.com.musics.domain.model.Genere;


public interface GenereRepository extends JpaRepository<Genere, Long>{

    Optional<Genere> findByName(String name);
}
