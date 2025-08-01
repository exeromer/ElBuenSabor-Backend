package com.powerRanger.ElBuenSabor.controllers;

import com.powerRanger.ElBuenSabor.dtos.CategoriaResponseDTO; // Importar DTO
import com.powerRanger.ElBuenSabor.entities.Categoria;
import com.powerRanger.ElBuenSabor.services.CategoriaService;
import jakarta.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.dao.DataIntegrityViolationException;

// Importar Map y HashMap si los usas para errores, aunque aquí no son necesarios si el servicio lanza excepciones claras
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api/categorias")
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    @GetMapping
    public ResponseEntity<List<CategoriaResponseDTO>> getAllCategorias() { // Devuelve Lista de DTOs
        try {
            List<CategoriaResponseDTO> categorias = categoriaService.getAllCategorias();
            if (categorias.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(categorias);
        } catch (Exception e) {
            // Loguear el error
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); // Build sin cuerpo para error interno
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCategoriaById(@PathVariable Integer id) { // Devuelve DTO o Error
        try {
            CategoriaResponseDTO categoriaDto = categoriaService.getCategoriaById(id);
            return ResponseEntity.ok(categoriaDto);
        } catch (Exception e) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("status", HttpStatus.NOT_FOUND.value());
            errorResponse.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        }
    }

    // Los métodos POST, PUT, DELETE se mantienen como estaban por ahora,
    // aceptando y devolviendo la entidad Categoria directamente,
    // o podrías refactorizarlos para usar CategoriaRequestDTO también.
    @PostMapping
    public ResponseEntity<?> createCategoria(@RequestBody Categoria categoria) { // Podría ser CategoriaRequestDTO
        try {
            Categoria nuevaCategoria = categoriaService.createCategoria(categoria);
            // Idealmente, al crear, también devolverías el CategoriaResponseDTO
            // CategoriaResponseDTO responseDto = categoriaService.convertToDto(nuevaCategoria); // Necesitarías acceso a convertToDto
            return new ResponseEntity<>(nuevaCategoria, HttpStatus.CREATED); // Por ahora, devolvemos la entidad
        } catch (Exception e) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("status", HttpStatus.BAD_REQUEST.value());
            errorResponse.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCategoria(@PathVariable Integer id, @RequestBody Categoria categoria) { // Podría ser CategoriaRequestDTO
        try {
            Categoria categoriaActualizada = categoriaService.updateCategoria(id, categoria);
            // Idealmente, también devolverías CategoriaResponseDTO
            return ResponseEntity.ok(categoriaActualizada); // Por ahora, entidad
        } catch (Exception e) {
            Map<String, Object> errorResponse = new HashMap<>();
            HttpStatus status = e.getMessage().contains("no encontrada") ? HttpStatus.NOT_FOUND : HttpStatus.BAD_REQUEST;
            errorResponse.put("status", status.value());
            errorResponse.put("error", e.getMessage());
            return ResponseEntity.status(status).body(errorResponse);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCategoria(@PathVariable Integer id) {
        try {
            categoriaService.deleteCategoria(id);
            return ResponseEntity.noContent().build();
        } catch (DataIntegrityViolationException e) {
            // FIX: Capturamos específicamente el error de la base de datos
            // y devolvemos un 409 Conflict, que es más apropiado.
            return handleGenericException(new Exception("No se puede eliminar la categoría porque está en uso por uno o más artículos."), HttpStatus.CONFLICT);
        } catch (Exception e) {
            // El resto de los errores (como no encontrar la categoría) pueden seguir siendo 404.
            return handleGenericException(e, HttpStatus.NOT_FOUND);
        }
    }
    private ResponseEntity<Map<String, Object>> handleConstraintViolation(ConstraintViolationException e) {
        Map<String, Object> errorResponse = new HashMap<>();
        errorResponse.put("status", HttpStatus.BAD_REQUEST.value());
        errorResponse.put("error", "Error de validación");
        errorResponse.put("mensajes", e.getConstraintViolations().stream()
                .map(cv -> cv.getPropertyPath() + ": " + cv.getMessage())
                .collect(Collectors.toList()));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    private ResponseEntity<Map<String, Object>> handleGenericException(Exception e, HttpStatus status) {
        Map<String, Object> errorResponse = new HashMap<>();
        errorResponse.put("status", status.value());
        errorResponse.put("error", e.getMessage());
        // Descomentar si necesitas ver la traza completa en los logs del backend
        // e.printStackTrace();
        return ResponseEntity.status(status).body(errorResponse);
    }
}