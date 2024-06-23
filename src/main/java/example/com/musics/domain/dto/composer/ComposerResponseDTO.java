package example.com.musics.domain.dto.composer;

import java.util.List;

import example.com.musics.domain.model.Music;

public class ComposerResponseDTO {
    
    private String name;
    private List<Music> musics;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public List<Music> getMusics() {
        return musics;
    }
    public void setMusics(List<Music> musics) {
        this.musics = musics;
    }
}
