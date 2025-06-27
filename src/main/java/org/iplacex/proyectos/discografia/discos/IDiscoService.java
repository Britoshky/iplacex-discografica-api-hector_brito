package org.iplacex.proyectos.discografia.discos;

import java.util.List;
import java.util.Optional;

public interface IDiscoService {
    DiscoDTO crearDisco(DiscoDTO dto);
    List<DiscoDTO> obtenerTodos();
    Optional<DiscoDTO> obtenerPorId(String id);
    List<DiscoDTO> obtenerPorIdArtista(String idArtista);
}
