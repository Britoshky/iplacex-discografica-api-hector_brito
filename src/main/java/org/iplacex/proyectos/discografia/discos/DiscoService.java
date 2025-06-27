package org.iplacex.proyectos.discografia.discos;

import org.iplacex.proyectos.discografia.artistas.IArtistaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DiscoService implements IDiscoService {

    private final IDiscoRepository discoRepo;
    private final IArtistaRepository artistaRepo;

    public DiscoService(IDiscoRepository discoRepo, IArtistaRepository artistaRepo) {
        this.discoRepo = discoRepo;
        this.artistaRepo = artistaRepo;
    }

    @Override
    public DiscoDTO crearDisco(DiscoDTO dto) {
        if (!artistaRepo.existsById(dto.getIdArtista())) {
            throw new IllegalArgumentException("El artista especificado no existe");
        }
        Disco disco = DiscoMapper.toEntity(dto);
        Disco guardado = discoRepo.save(disco);
        return DiscoMapper.toDTO(guardado);
    }

    @Override
    public List<DiscoDTO> obtenerTodos() {
        return discoRepo.findAll().stream()
                .map(DiscoMapper::toDTO)
                .toList();
    }

    @Override
    public Optional<DiscoDTO> obtenerPorId(String id) {
        return discoRepo.findById(id)
                .map(DiscoMapper::toDTO);
    }

    @Override
    public List<DiscoDTO> obtenerPorIdArtista(String idArtista) {
        return discoRepo.findDiscosByIdArtista(idArtista).stream()
                .map(DiscoMapper::toDTO)
                .toList();
    }
}
