package example.com.musics.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import example.com.musics.domain.model.Composer;

public interface ComposerRepository extends JpaRepository<Composer, Long> {
    
    List<Composer> findByName(String name);
}
