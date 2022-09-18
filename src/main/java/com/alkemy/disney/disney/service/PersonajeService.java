package com.alkemy.disney.disney.service;

import com.alkemy.disney.disney.dto.PersonajeDTO;
import com.alkemy.disney.disney.entity.PersonajeEntity;

import java.util.List;
import java.util.Set;

public interface PersonajeService {

    PersonajeDTO edit (Long id, PersonajeDTO dto);
    List<PersonajeDTO> getByFilters(String name, int age, Set<Long> movies, String order);

    List<PersonajeDTO> getAllPersonajes();

    void delete(Long id);

    PersonajeDTO save(PersonajeDTO dto);

    PersonajeDTO buscarPorId(Long id);

    void addPelicula(Long id, Long idPeliculaOSerie);

    void removePelicula(Long id, Long idPeliculaOSerie);

    PersonajeEntity getEntityById(Long id);

    Set<PersonajeEntity> buscarEntitiesPorId(Set<Long> personajesId);
}
