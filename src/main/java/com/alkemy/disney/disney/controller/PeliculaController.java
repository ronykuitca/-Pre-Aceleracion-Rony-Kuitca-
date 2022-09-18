package com.alkemy.disney.disney.controller;

import com.alkemy.disney.disney.dto.PeliculaDTO;
import com.alkemy.disney.disney.service.PeliculaService;
import com.alkemy.disney.disney.service.impl.PeliculaServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("peliculas")
public class PeliculaController {

    @Lazy


    @Autowired
    private PeliculaService peliculaService;

    @GetMapping("/{id}")
    public ResponseEntity<PeliculaDTO> getById(@PathVariable Long id) {
        PeliculaDTO dto = peliculaService.buscarPorId(id);

        return ResponseEntity.ok().body(dto);
    }

    @GetMapping(path = "/")
    public ResponseEntity<List<PeliculaDTO>> getAll() {
        List<PeliculaDTO> peliculas = peliculaService.getAllPeliculas();
        return ResponseEntity.ok().body(peliculas);
    }

    @GetMapping
    public ResponseEntity<List<PeliculaDTO>> getDetailsByFilters(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Long genre,
            @RequestParam(required = false, defaultValue = "ASC") String order
    ) {

        List<PeliculaDTO> peliculas = peliculaService.getByFilters(name,genre,order);

        return ResponseEntity.ok(peliculas);
    }

    @PostMapping
    public ResponseEntity<PeliculaDTO> save(@RequestBody PeliculaDTO dto) {
        PeliculaDTO peliculaGuardada = peliculaService.save(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(peliculaGuardada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete (@PathVariable Long id) {
        peliculaService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<PeliculaDTO> edit(@PathVariable Long id, @RequestBody PeliculaDTO dto) {
        return ResponseEntity.status(HttpStatus.OK).body(peliculaService.edit(id,dto));
    }

    @PostMapping("/{id}/characters/{idPersonaje}")
    public ResponseEntity<Void> addPersonaje(@PathVariable Long id, @PathVariable Long idPersonaje) {
        peliculaService.addPersonaje(id, idPersonaje);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }


    @DeleteMapping("/{id}/characters/{idPersonaje}")
    public ResponseEntity<Void> removePersonaje(@PathVariable Long id, @PathVariable Long idPersonaje) {
        peliculaService.removePersonaje(id, idPersonaje);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }


}
