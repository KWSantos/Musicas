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

import example.com.musics.domain.dto.genere.GenereRequestDTO;
import example.com.musics.domain.dto.genere.GenereResponseDTO;
import example.com.musics.domain.service.GenereService;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/generes")
public class GenereController {
    
    @Autowired
    private GenereService genereService;

    @GetMapping
    public ResponseEntity<List<GenereResponseDTO>> getAll() {
        return ResponseEntity.ok(genereService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<GenereResponseDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(genereService.getById(id));
    }

    @GetMapping("/search")
    public ResponseEntity<GenereResponseDTO> getByName(@RequestParam String name) {
        return ResponseEntity.ok(genereService.getByName(name));
    }

    @PostMapping
    public ResponseEntity<GenereResponseDTO> register(@RequestBody GenereRequestDTO dto) {
        GenereResponseDTO artist = genereService.register(dto);
        return new ResponseEntity<GenereResponseDTO>(artist, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<GenereResponseDTO> update(@PathVariable Long id, @RequestBody GenereRequestDTO dto) {
        GenereResponseDTO artist = genereService.update(id, dto);
        return new ResponseEntity<GenereResponseDTO>(artist, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        genereService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
