package org.iplacex.proyectos.discografia.artistas;

import java.util.List;
import java.util.Optional;

public interface IArtistaService {
    ArtistaDTO crearArtista(ArtistaDTO dto);
    List<ArtistaDTO> obtenerTodos();
    Optional<ArtistaDTO> obtenerPorId(String id);
    Optional<ArtistaDTO> actualizarArtista(String id, ArtistaDTO dto);
    boolean eliminarArtista(String id);
}
