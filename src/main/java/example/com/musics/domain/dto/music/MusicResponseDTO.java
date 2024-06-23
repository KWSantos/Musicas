package example.com.musics.domain.dto.music;

import example.com.musics.domain.model.Artist;
import example.com.musics.domain.model.Composer;
import example.com.musics.domain.model.Genere;

public class MusicResponseDTO {
    
    private String name;
    private int compositionYear;
    private Artist artist;
    private Composer composer;
    private Genere genre;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getCompositionYear() {
        return compositionYear;
    }
    public void setCompositionYear(int compositionYear) {
        this.compositionYear = compositionYear;
    }
    public Artist getArtist() {
        return artist;
    }
    public void setArtist(Artist artist) {
        this.artist = artist;
    }
    public Composer getComposer() {
        return composer;
    }
    public void setComposer(Composer composer) {
        this.composer = composer;
    }
    public Genere getGenre() {
        return genre;
    }
    public void setGenre(Genere genre) {
        this.genre = genre;
    }
    
}
