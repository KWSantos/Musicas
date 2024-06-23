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

import example.com.musics.domain.dto.composer.ComposerRequestDTO;
import example.com.musics.domain.dto.composer.ComposerResponseDTO;
import example.com.musics.domain.service.ComposerService;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/composers")
public class ComposerController {
    
    @Autowired
    private ComposerService composerService;

    @GetMapping
    public ResponseEntity<List<ComposerResponseDTO>> getAll() {
        return ResponseEntity.ok(composerService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ComposerResponseDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(composerService.getById(id));
    }

    @GetMapping("/search")
    public ResponseEntity<List<ComposerResponseDTO>> getByName(@RequestParam String name) {
        return ResponseEntity.ok(composerService.getByName(name));
    }

    @PostMapping
    public ResponseEntity<ComposerResponseDTO> register(@RequestBody ComposerRequestDTO dto) {
        ComposerResponseDTO artist = composerService.register(dto);
        return new ResponseEntity<ComposerResponseDTO>(artist, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ComposerResponseDTO> update(@PathVariable Long id, @RequestBody ComposerRequestDTO dto) {
        ComposerResponseDTO artist = composerService.update(id, dto);
        return new ResponseEntity<ComposerResponseDTO>(artist, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        composerService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
