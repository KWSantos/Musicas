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

import example.com.musics.domain.dto.artist.ArtistRequestDTO;
import example.com.musics.domain.dto.artist.ArtistResponseDTO;
import example.com.musics.domain.service.ArtistService;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/artists")
public class ArtistController {
    
    @Autowired
    private ArtistService artistService;

    @GetMapping
    public ResponseEntity<List<ArtistResponseDTO>> getAll() {
        return ResponseEntity.ok(artistService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ArtistResponseDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(artistService.getById(id));
    }

    @GetMapping("/search")
    public ResponseEntity<List<ArtistResponseDTO>> getByName(@RequestParam String name) {
        return ResponseEntity.ok(artistService.getByName(name));
    }

    @PostMapping
    public ResponseEntity<ArtistResponseDTO> register(@RequestBody ArtistRequestDTO dto) {
        ArtistResponseDTO artist = artistService.register(dto);
        return new ResponseEntity<ArtistResponseDTO>(artist, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ArtistResponseDTO> update(@PathVariable Long id, @RequestBody ArtistRequestDTO dto) {
        ArtistResponseDTO artist = artistService.update(id, dto);
        return new ResponseEntity<ArtistResponseDTO>(artist, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        artistService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
