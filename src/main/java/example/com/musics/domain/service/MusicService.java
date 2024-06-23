package example.com.musics.domain.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import example.com.musics.domain.dto.music.MusicRequestDTO;
import example.com.musics.domain.dto.music.MusicResponseDTO;
import example.com.musics.domain.exception.ResourceBadRequestException;
import example.com.musics.domain.model.Artist;
import example.com.musics.domain.model.Composer;
import example.com.musics.domain.model.Genere;
import example.com.musics.domain.model.Music;
import example.com.musics.domain.repository.MusicRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

@Service
public class MusicService implements ICRUDService<MusicRequestDTO, MusicResponseDTO>{

    private EntityManager entityManager;

    @Autowired
    private MusicRepository musicRepository;

    @Autowired
    private ModelMapper mapper;

    @Override
    public List<MusicResponseDTO> getAll() {
        List<Music> musics = musicRepository.findAll();
        return musics.stream().map(music -> mapper.map(music, MusicResponseDTO.class)).collect(Collectors.toList());
    }

    @Override
    public MusicResponseDTO getById(Long id) {
        Optional<Music> optMusic = musicRepository.findById(id);
        return mapper.map(optMusic.get(), MusicResponseDTO.class);
    }

    public List<MusicResponseDTO> searchMusics(String name, int compositionYear, Artist artist, Composer composer, Genere genere) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Music> query = cb.createQuery(Music.class);
        Root<Music> root = query.from(Music.class);

        Predicate predicate = cb.conjunction();
        if(name != null) {
            predicate = cb.and(predicate, cb.equal(root.get("name"), name));
        }
        if(compositionYear != 0) {
            predicate = cb.and(predicate, cb.equal(root.get("compositionYear"), compositionYear));
        }
        if(artist != null) {
            predicate = cb.and(predicate, cb.equal(root.get("idArtist"), artist.getId()));
        }
        if(composer != null) {
            predicate = cb.and(predicate, cb.equal(root.get("idComposer"), composer.getId()));
        }
        if(genere != null) {
            predicate = cb.and(predicate, cb.equal(root.get("idGenere"), genere.getId()));
        }

        query.where(predicate);
        return entityManager.createQuery(query).getResultList().stream().map(music -> mapper.map(music, MusicResponseDTO.class)).collect(Collectors.toList());
    }

    @Override
    public MusicResponseDTO register(MusicRequestDTO dto) {
        if(dto.getArtist() == null || dto.getComposer() == null || dto.getName() == null) {
            throw new ResourceBadRequestException("Nome, Artista e Compositor sao campos obrigatorios");
        }
        Music music = mapper.map(dto, Music.class);
        music = musicRepository.save(music);
        return mapper.map(music, MusicResponseDTO.class);
    }

    @Override
    public MusicResponseDTO update(Long id, MusicRequestDTO dto) {
        if(dto.getArtist() == null || dto.getComposer() == null || dto.getName() == null) {
            throw new ResourceBadRequestException("Nome, Artista e Compositor sao campos obrigatorios");
        }
        Music music = mapper.map(dto, Music.class);
        music.setId(id);
        music.setArtist(dto.getArtist());
        music.setComposer(dto.getComposer());
        music.setCompositionYear(dto.getCompositionYear());
        music.setGenre(dto.getGenre());
        music = musicRepository.save(music);
        return mapper.map(music, MusicResponseDTO.class);
    }

    @Override
    public void delete(Long id) {
        musicRepository.deleteById(id);
    }
    
}
