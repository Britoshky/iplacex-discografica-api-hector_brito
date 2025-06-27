package org.iplacex.proyectos.discografia.artistas;

import jakarta.validation.Valid;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import java.util.List;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class ArtistaController {

    private static final String MSG_NO_ENCONTRADO = "Artista no encontrado";
    private final IArtistaService service;

    public ArtistaController(IArtistaService service) {
        this.service = service;
    }

    @Operation(summary = "Crear un nuevo artista")
    @PostMapping(value = "/artista", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ArtistaDTO> crear(@Valid @RequestBody ArtistaDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.crearArtista(dto));
    }

    @Operation(summary = "Listar todos los artistas")
    @GetMapping("/artistas")
    public ResponseEntity<List<ArtistaDTO>> listar() {
        return ResponseEntity.ok(service.obtenerTodos());
    }

    @Operation(summary = "Obtener artista por ID")
    @GetMapping("/artista/{id}")
    public ResponseEntity<Object> obtener(@PathVariable String id) {
        return service.obtenerPorId(id)
                .<ResponseEntity<Object>>map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(MSG_NO_ENCONTRADO));
    }

    @Operation(summary = "Actualizar artista por ID")
    @PutMapping("/artista/{id}")
    public ResponseEntity<Object> actualizar(@PathVariable String id, @Valid @RequestBody ArtistaDTO dto) {
        return service.actualizarArtista(id, dto)
                .<ResponseEntity<Object>>map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(MSG_NO_ENCONTRADO));
    }

    @Operation(summary = "Eliminar artista por ID")
    @DeleteMapping("/artista/{id}")
    public ResponseEntity<Object> eliminar(@PathVariable String id) {
        return service.eliminarArtista(id)
                ? ResponseEntity.ok("Artista eliminado")
                : ResponseEntity.status(HttpStatus.NOT_FOUND).body(MSG_NO_ENCONTRADO);
    }
}
