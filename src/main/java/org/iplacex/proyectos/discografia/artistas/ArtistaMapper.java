package org.iplacex.proyectos.discografia.artistas;

public final class ArtistaMapper {
    private ArtistaMapper() {}

    public static ArtistaDTO toDTO(Artista artista) {
        return new ArtistaDTO(
                artista.getNombre(),
                artista.getEstilos(),
                artista.getAnioFundacion(),
                artista.isEstaActivo());
    }

    public static Artista toEntity(ArtistaDTO dto) {
        Artista artista = new Artista();
        artista.setNombre(dto.getNombre());
        artista.setEstilos(dto.getEstilos());
        artista.setAnioFundacion(dto.getAnioFundacion());
        artista.setEstaActivo(dto.isEstaActivo());
        return artista;
    }
}
