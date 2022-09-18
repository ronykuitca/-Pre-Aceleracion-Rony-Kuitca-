package com.alkemy.disney.disney.mapper;

import com.alkemy.disney.disney.dto.PeliculaDTO;
import com.alkemy.disney.disney.dto.PersonajeDTO;
import com.alkemy.disney.disney.entity.PersonajeEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Component
public class PersonajeMapper {

    @Lazy

    @Autowired
    private PeliculaMapper peliculaMapper;

    public PersonajeEntity personajeDTO2Entity(PersonajeDTO dto) {
        PersonajeEntity entity = new PersonajeEntity();
        entity.setImagen(dto.getImagen());
        entity.setNombre(dto.getNombre());
        entity.setEdad(dto.getEdad());
        entity.setPeso(dto.getPeso());
        entity.setHistoria(dto.getHistoria());

        return entity;
    }


    public PersonajeDTO personajeEntity2DTO(PersonajeEntity entity, boolean loadPelis) {
        PersonajeDTO dto = new PersonajeDTO();
        dto.setId(entity.getId());
        dto.setImagen(entity.getImagen());
        dto.setNombre(entity.getNombre());
        dto.setEdad(entity.getEdad());
        dto.setPeso(entity.getPeso());
        dto.setHistoria(entity.getHistoria());

        if (loadPelis) {
            List<PeliculaDTO> peliculaDTO = peliculaMapper.peliculaEntityList2DTOList(entity.getPeliculas(), false);
            dto.setPeliculas(peliculaDTO);
        }
        return dto;
    }

    public List<PersonajeDTO> personajeEntityList2DTOList(List<PersonajeEntity> entidades) {
        List<PersonajeDTO> dtos = new ArrayList<>();

        for (PersonajeEntity p: entidades) {
            dtos.add(personajeEntity2DTO(p, false));
        }

        return dtos;
    }

    public PersonajeEntity editEntity(PersonajeEntity entity, PersonajeDTO dto){

        entity.setHistoria(dto.getHistoria());
        entity.setEdad(dto.getEdad());
        entity.setNombre(dto.getNombre());
        entity.setImagen(dto.getImagen());
        entity.setPeso(dto.getPeso());

        return entity;
    }

    public List<PersonajeDTO> personajeEntitySet2DTOList  (Set<PersonajeEntity> personajeEntities, boolean loadPelis) {
        List<PersonajeDTO> listaDTOs = new ArrayList<>();
        for (PersonajeEntity entity : personajeEntities) {
            listaDTOs.add(personajeEntity2DTO(entity, loadPelis));
        }
        return listaDTOs;
    }
}
