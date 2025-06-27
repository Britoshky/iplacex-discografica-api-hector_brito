package org.iplacex.proyectos.discografia.discos;

import jakarta.validation.Valid;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class DiscoController {

    private final IDiscoService service;

    public DiscoController(IDiscoService service) {
        this.service = service;
    }

    @Operation(summary = "Crear un nuevo disco")
    @ApiResponse(responseCode = "201", description = "Disco creado exitosamente")
    @PostMapping(value = "/disco", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> crear(@Valid @RequestBody DiscoDTO dto) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(service.crearDisco(dto));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @Operation(summary = "Listar todos los discos")
    @GetMapping(value = "/discos", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<DiscoDTO>> listar() {
        return ResponseEntity.ok(service.obtenerTodos());
    }

    @Operation(summary = "Obtener disco por ID")
    @GetMapping(value = "/disco/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> obtener(@PathVariable String id) {
        return service.obtenerPorId(id)
                .<ResponseEntity<Object>>map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body("Disco no encontrado"));
    }

    @Operation(summary = "Listar discos por ID de artista")
    @GetMapping(value = "/artista/{id}/discos", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<DiscoDTO>> listarPorArtista(@PathVariable String id) {
        return ResponseEntity.ok(service.obtenerPorIdArtista(id));
    }
}
