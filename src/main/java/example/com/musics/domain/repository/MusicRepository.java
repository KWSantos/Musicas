package example.com.musics.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import example.com.musics.domain.model.Artist;
import example.com.musics.domain.model.Composer;
import example.com.musics.domain.model.Genere;
import example.com.musics.domain.model.Music;

public interface MusicRepository extends JpaRepository<Music, Long> {
    
    List<Music> findByName(String name);
    List<Music> findByArtist(Artist artist);
    List<Music> findByComposer(Composer composer);
    List<Music> findByGenre(Genere genre);
    List<Music> findByCompositionYear(int compositionYear);
}
