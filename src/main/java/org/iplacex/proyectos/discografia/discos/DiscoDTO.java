package org.iplacex.proyectos.discografia.discos;

import jakarta.validation.constraints.*;

import java.time.Year;
import java.util.List;

public class DiscoDTO {

    @NotBlank(message = "El ID del artista es obligatorio")
    private String idArtista;

    @NotBlank(message = "El nombre del disco es obligatorio")
    private String nombre;

    @Min(value = 1900, message = "El año debe ser mayor a 1900")
    @Max(value = 2100, message = "El año debe ser menor a 2100")
    private int anioLanzamiento;

    @NotEmpty(message = "Debe haber al menos una canción")
    private List<@NotBlank(message = "El nombre de la canción no puede estar vacío") String> canciones;

    public DiscoDTO() {}

    public DiscoDTO(String idArtista, String nombre, int anioLanzamiento, List<String> canciones) {
        if (anioLanzamiento > Year.now().getValue()) {
            throw new IllegalArgumentException("El año de lanzamiento no puede estar en el futuro");
        }
        this.idArtista = idArtista;
        this.nombre = nombre;
        this.anioLanzamiento = anioLanzamiento;
        this.canciones = canciones;
    }

    public String getIdArtista() {
        return idArtista;
    }

    public void setIdArtista(String idArtista) {
        this.idArtista = idArtista;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getAnioLanzamiento() {
        return anioLanzamiento;
    }

    public void setAnioLanzamiento(int anioLanzamiento) {
        this.anioLanzamiento = anioLanzamiento;
    }

    public List<String> getCanciones() {
        return canciones;
    }

    public void setCanciones(List<String> canciones) {
        this.canciones = canciones;
    }
}
