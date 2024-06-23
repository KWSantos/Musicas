package example.com.musics.domain.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import example.com.musics.domain.dto.composer.ComposerRequestDTO;
import example.com.musics.domain.dto.composer.ComposerResponseDTO;
import example.com.musics.domain.exception.ResourceBadRequestException;
import example.com.musics.domain.exception.ResourceNotFoundException;
import example.com.musics.domain.model.Composer;
import example.com.musics.domain.repository.ComposerRepository;

public class ComposerService implements ICRUDService<ComposerRequestDTO, ComposerResponseDTO>{

    @Autowired
    private ComposerRepository composerRepository;

    @Autowired
    private ModelMapper mapper;

    @Override
    public List<ComposerResponseDTO> getAll() {
        List<Composer> composers = composerRepository.findAll();
        return composers.stream().map(composer -> mapper.map(composer, ComposerResponseDTO.class)).collect(Collectors.toList());
    }

    @Override
    public ComposerResponseDTO getById(Long id) {
        Optional<Composer> optComposer = composerRepository.findById(id);
        if(optComposer.isEmpty()) {
            throw new ResourceNotFoundException("Não foi possível encontrar o compositor com o id" + id);
        }
        return mapper.map(optComposer.get(), ComposerResponseDTO.class);
    }

    public List<ComposerResponseDTO> getByName(String name) {
        List<Composer> composers = composerRepository.findByName(name);
        return composers.stream().map(composer -> mapper.map(composer, ComposerResponseDTO.class)).collect(Collectors.toList());
    }

    @Override
    public ComposerResponseDTO register(ComposerRequestDTO dto) {
        if(dto.getName() == null) {
            throw new ResourceBadRequestException("O nome é obrigatório");
        }
        Composer composer = mapper.map(dto, Composer.class);
        composer = composerRepository.save(composer);
        return mapper.map(composer, ComposerResponseDTO.class);
    }

    @Override
    public ComposerResponseDTO update(Long id, ComposerRequestDTO dto) {
        if(dto.getName() == null) {
            throw new ResourceBadRequestException("O nome é obrigatório");
        }
        Composer composer = mapper.map(dto, Composer.class);
        composer.setId(id);
        composer.setName(dto.getName());
        composer = composerRepository.save(composer);
        return mapper.map(composer, ComposerResponseDTO.class);
    }

    @Override
    public void delete(Long id) {
        composerRepository.deleteById(id);
    }
    
}
