package example.com.musics.domain.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import example.com.musics.domain.dto.artist.ArtistRequestDTO;
import example.com.musics.domain.dto.artist.ArtistResponseDTO;
import example.com.musics.domain.exception.ResourceBadRequestException;
import example.com.musics.domain.exception.ResourceNotFoundException;
import example.com.musics.domain.model.Artist;
import example.com.musics.domain.repository.ArtistRepository;

@Service
public class ArtistService implements ICRUDService<ArtistRequestDTO, ArtistResponseDTO>{

    @Autowired
    private ArtistRepository artistRepository;

    @Autowired
    private ModelMapper mapper;

    @Override
    public List<ArtistResponseDTO> getAll() {
        List<Artist> artists = artistRepository.findAll();
        return artists.stream().map(artist -> mapper.map(artist, ArtistResponseDTO.class)).collect(Collectors.toList());
    }

    @Override
    public ArtistResponseDTO getById(Long id) {
        Optional<Artist> optArtist = artistRepository.findById(id);
        if(optArtist.isEmpty()){
            throw new ResourceNotFoundException("Não foi possível encontrar o artista com o id" + id);
        }
        return mapper.map(optArtist.get(), ArtistResponseDTO.class);
    }

    public List<ArtistResponseDTO> getByName(String name) {
        List<Artist> artists = artistRepository.findByName(name);
        return artists.stream().map(artist -> mapper.map(artist, ArtistResponseDTO.class)).collect(Collectors.toList());
    }

    @Override
    public ArtistResponseDTO register(ArtistRequestDTO dto) {
        if(dto.getName() == null) {
            throw new ResourceBadRequestException("O nome é obrigatório");
        }
        Artist artist = mapper.map(dto, Artist.class);
        artist = artistRepository.save(artist);
        return mapper.map(artist, ArtistResponseDTO.class);
    }

    @Override
    public ArtistResponseDTO update(Long id, ArtistRequestDTO dto) {
        if(dto.getName() == null) {
            throw new ResourceBadRequestException("O nome é obrigatório");
        }
        Artist artist = mapper.map(dto, Artist.class);
        artist.setId(id);
        artist.setName(dto.getName());
        artist = artistRepository.save(artist);
        return mapper.map(artist, ArtistResponseDTO.class);
    }

    @Override
    public void delete(Long id) {
        artistRepository.deleteById(id);
    }
    
}
