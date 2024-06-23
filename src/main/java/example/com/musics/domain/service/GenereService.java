package example.com.musics.domain.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import example.com.musics.domain.dto.genere.GenereRequestDTO;
import example.com.musics.domain.dto.genere.GenereResponseDTO;
import example.com.musics.domain.exception.ResourceBadRequestException;
import example.com.musics.domain.exception.ResourceNotFoundException;
import example.com.musics.domain.model.Genere;
import example.com.musics.domain.repository.GenereRepository;

@Service
public class GenereService implements ICRUDService<GenereRequestDTO, GenereResponseDTO>{

    @Autowired
    private GenereRepository genereRepository;

    @Autowired
    private ModelMapper mapper;

    @Override
    public List<GenereResponseDTO> getAll() {
        List<Genere> generes = genereRepository.findAll();
        return generes.stream().map(genere -> mapper.map(genere, GenereResponseDTO.class)).collect(Collectors.toList());
    }

    @Override
    public GenereResponseDTO getById(Long id) {
        Optional<Genere> optGenere = genereRepository.findById(id);
        if(optGenere.isEmpty()) {
            throw new ResourceNotFoundException("Não foi possível encontrar o genero com o id" + id);
        }
        return mapper.map(optGenere.get(), GenereResponseDTO.class);
    }

    public GenereResponseDTO getByName(String name) {
        Optional<Genere> optGenere = genereRepository.findByName(name);
        if(optGenere.isEmpty()) {
            throw new ResourceNotFoundException("Não foi possível encontrar o genero com o nome" + name);
        }
        return mapper.map(optGenere.get(), GenereResponseDTO.class);
    }

    @Override
    public GenereResponseDTO register(GenereRequestDTO dto) {
        if(dto.getName() == null) {
            throw new ResourceBadRequestException("O nome é obrigatório");
        }
        Genere genere = mapper.map(dto, Genere.class);
        genere = genereRepository.save(genere);
        return mapper.map(genere, GenereResponseDTO.class);
    }

    @Override
    public GenereResponseDTO update(Long id, GenereRequestDTO dto) {
        if(dto.getName() == null) {
            throw new ResourceBadRequestException("O nome é obrigatório");
        }
        Genere genere = mapper.map(dto, Genere.class);
        genere.setId(id);
        genere.setName(dto.getName());
        genere = genereRepository.save(genere);
        return mapper.map(genere, GenereResponseDTO.class);
    }

    @Override
    public void delete(Long id) {
        genereRepository.deleteById(id);
    }
    
}
