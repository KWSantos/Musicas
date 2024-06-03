package example.com.musics.domain.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Music {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @ManyToOne
    @JoinColumn(name = "idArtist")
    private Artist artist;
    @ManyToOne
    @JoinColumn(name = "idComposer")
    private Composer composer;
    @ManyToOne
    @JoinColumn(name = "idGenre")
    private Genere genre;
    private int compositionYear;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
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
    public int getCompositionYear() {
        return compositionYear;
    }
    public void setCompositionYear(int compositionYear) {
        this.compositionYear = compositionYear;
    }
    
}
