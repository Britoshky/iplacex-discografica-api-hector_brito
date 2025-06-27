package org.iplacex.proyectos.discografia.artistas;

import jakarta.validation.constraints.*;
import java.time.Year;
import java.util.List;

public class ArtistaDTO {
    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;

    @NotEmpty(message = "Debe haber al menos un estilo")
    private List<@NotBlank(message = "El estilo no puede estar vacío") String> estilos;

    @Min(value = 1900, message = "El año debe ser mayor a 1900")
    @Max(value = 2100, message = "El año debe ser menor a 2100")
    private int anioFundacion;

    private boolean estaActivo;

    public ArtistaDTO() {}

    public ArtistaDTO(String nombre, List<String> estilos, int anioFundacion, boolean estaActivo) {
        this.nombre = nombre;
        this.estilos = estilos;
        setAnioFundacion(anioFundacion);
        this.estaActivo = estaActivo;
    }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public List<String> getEstilos() { return estilos; }
    public void setEstilos(List<String> estilos) { this.estilos = estilos; }

    public int getAnioFundacion() { return anioFundacion; }
    public void setAnioFundacion(int anioFundacion) {
        int anioActual = Year.now().getValue();
        if (anioFundacion > anioActual) {
            throw new IllegalArgumentException("El año de fundación no puede ser en el futuro");
        }
        this.anioFundacion = anioFundacion;
    }

    public boolean isEstaActivo() { return estaActivo; }
    public void setEstaActivo(boolean estaActivo) { this.estaActivo = estaActivo; }
}
