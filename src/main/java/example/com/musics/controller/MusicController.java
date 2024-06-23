package example.com.musics.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import example.com.musics.domain.dto.music.MusicRequestDTO;
import example.com.musics.domain.dto.music.MusicResponseDTO;
import example.com.musics.domain.model.Artist;
import example.com.musics.domain.model.Composer;
import example.com.musics.domain.model.Genere;
import example.com.musics.domain.service.MusicService;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/musics")
public class MusicController {
    
    @Autowired
    private MusicService musicService;

    @GetMapping
    public ResponseEntity<List<MusicResponseDTO>> getAll() {
        return ResponseEntity.ok(musicService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MusicResponseDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(musicService.getById(id));
    }

    @GetMapping("/search")
    public ResponseEntity<List<MusicResponseDTO>> searchMusic(
        @RequestParam(required = false) String name,
        @RequestParam(required = false) int compositionYear,
        @RequestParam(required = false) Artist artist,
        @RequestParam(required = false) Composer composer,
        @RequestParam(required = false) Genere genere
    ) {
        List<MusicResponseDTO> musics = musicService.searchMusics(name, compositionYear, artist, composer, genere);
        return ResponseEntity.ok(musics);
    }

    @PostMapping
    public ResponseEntity<MusicResponseDTO> register(@RequestBody MusicRequestDTO dto) {
        MusicResponseDTO music = musicService.register(dto);
        return new ResponseEntity<MusicResponseDTO>(music, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MusicResponseDTO> update(@PathVariable Long id, @RequestBody MusicRequestDTO dto) {
        MusicResponseDTO music = musicService.update(id, dto);
        return new ResponseEntity<MusicResponseDTO>(music, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        musicService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
