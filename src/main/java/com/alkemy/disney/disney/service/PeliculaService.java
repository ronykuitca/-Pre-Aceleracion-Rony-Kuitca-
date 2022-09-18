package com.alkemy.disney.disney.service;

import com.alkemy.disney.disney.dto.PeliculaDTO;
import com.alkemy.disney.disney.dto.PersonajeDTO;
import com.alkemy.disney.disney.entity.PeliculaEntity;

import java.util.List;

public interface PeliculaService {
    List<PeliculaDTO> getAllPeliculas();

    PeliculaDTO edit (Long id, PeliculaDTO dto);

    PeliculaDTO save(PeliculaDTO dto);

    void delete (Long id);

    List<PeliculaDTO> getByFilters(String name, Long genre, String order);

    PeliculaDTO buscarPorId(Long id);

    PeliculaEntity getEntityById(Long id);

    void addPersonaje(Long id, Long idPersonaje);

    void removePersonaje(Long id, Long idPersonaje);
}
