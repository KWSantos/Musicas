package example.com.musics.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import example.com.musics.domain.model.Artist;
import java.util.List;


public interface ArtistRepository extends JpaRepository<Artist, Long>{
    
    List<Artist> findByName(String name);
}
