package org.iplacex.proyectos.discografia.discos;

public final class DiscoMapper {

    private DiscoMapper() {}

    public static Disco toEntity(DiscoDTO dto) {
        Disco disco = new Disco();
        disco.setIdArtista(dto.getIdArtista());
        disco.setNombre(dto.getNombre());
        disco.setAnioLanzamiento(dto.getAnioLanzamiento());
        disco.setCanciones(dto.getCanciones());
        return disco;
    }

    public static DiscoDTO toDTO(Disco disco) {
        return new DiscoDTO(
                disco.getIdArtista(),
                disco.getNombre(),
                disco.getAnioLanzamiento(),
                disco.getCanciones()
        );
    }
}
