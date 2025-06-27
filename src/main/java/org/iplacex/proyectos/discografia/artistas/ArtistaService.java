package org.iplacex.proyectos.discografia.artistas;

import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ArtistaService implements IArtistaService {

    private final IArtistaRepository repo;

    public ArtistaService(IArtistaRepository repo) {
        this.repo = repo;
    }

    @Override
    public ArtistaDTO crearArtista(ArtistaDTO dto) {
        Artista artista = ArtistaMapper.toEntity(dto);
        return ArtistaMapper.toDTO(repo.save(artista));
    }

    @Override
    public List<ArtistaDTO> obtenerTodos() {
        return repo.findAll().stream()
                .map(ArtistaMapper::toDTO)
                .toList();
    }

    @Override
    public Optional<ArtistaDTO> obtenerPorId(String id) {
        return repo.findById(id).map(ArtistaMapper::toDTO);
    }

    @Override
    public Optional<ArtistaDTO> actualizarArtista(String id, ArtistaDTO dto) {
        if (!repo.existsById(id)) {
            return Optional.empty();
        }
        Artista artista = ArtistaMapper.toEntity(dto);
        artista.setId(id);
        return Optional.of(ArtistaMapper.toDTO(repo.save(artista)));
    }

    @Override
    public boolean eliminarArtista(String id) {
        if (!repo.existsById(id)) {
            return false;
        }
        repo.deleteById(id);
        return true;
    }
}
