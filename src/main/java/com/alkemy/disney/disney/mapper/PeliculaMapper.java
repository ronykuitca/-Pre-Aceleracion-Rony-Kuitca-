package com.alkemy.disney.disney.mapper;

import com.alkemy.disney.disney.dto.PeliculaDTO;
import com.alkemy.disney.disney.dto.PersonajeDTO;
import com.alkemy.disney.disney.entity.GeneroEntity;
import com.alkemy.disney.disney.entity.PeliculaEntity;
import com.alkemy.disney.disney.entity.PersonajeEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Component
public class PeliculaMapper {

    @Lazy

    @Autowired
    private GeneroMapper generoMapper;

    @Autowired
    private PersonajeMapper personajeMapper;

    public PeliculaDTO peliculaEntity2DTO(PeliculaEntity entity, boolean loadPersonajes) {
        PeliculaDTO dto = new PeliculaDTO();
        dto.setId(entity.getId());
        dto.setCalificacion(entity.getCalificacion());
        dto.setImagen(entity.getImagen());
        dto.setFechaCreacion(entity.getFechaCreacion());
        dto.setTitulo(entity.getTitulo());
        dto.setGeneroId(entity.getGeneroId());
        dto.setGeneroDTO(generoMapper.generoEntity2DTO(entity.getGenero()));

        if (loadPersonajes) {
            List<PersonajeDTO> personajeDTOs = personajeMapper.personajeEntitySet2DTOList(entity.getPersonajes(), false);
            dto.setPersonajes(personajeDTOs);
        }

        return dto;
    }

    public List<PeliculaDTO> peliculaEntityList2DTOList(List<PeliculaEntity> entities, boolean loadPersonajes) {
        List<PeliculaDTO> listaDTO = new ArrayList<>();
        for (PeliculaEntity p: entities) {
            listaDTO.add(peliculaEntity2DTO(p, loadPersonajes));
        }
        return listaDTO;
    }

    public PeliculaEntity peliculaDTO2Entity(PeliculaDTO dto, GeneroEntity genero, Set<PersonajeEntity> personajes) {
        PeliculaEntity entity = new PeliculaEntity();
        entity.setImagen(dto.getImagen());
        entity.setTitulo(dto.getTitulo());
        entity.setFechaCreacion(dto.getFechaCreacion());
        entity.setCalificacion(dto.getCalificacion());
        entity.setGenero(genero);
        entity.setGeneroId(dto.getGeneroId());

        if (!CollectionUtils.isEmpty(personajes)) {
            entity.setPersonajes(personajes);
        }
        return entity;
    }

    public PeliculaEntity editEntity(PeliculaEntity entity, PeliculaDTO dto) {
        entity.setImagen(dto.getImagen());
        entity.setTitulo(dto.getTitulo());
        entity.setFechaCreacion(dto.getFechaCreacion());
        entity.setCalificacion(dto.getCalificacion());
        entity.setGeneroId(dto.getGeneroId());

        return entity;
    }
}
