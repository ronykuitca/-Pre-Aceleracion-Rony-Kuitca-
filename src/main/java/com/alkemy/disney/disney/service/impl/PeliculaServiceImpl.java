package com.alkemy.disney.disney.service.impl;

import com.alkemy.disney.disney.dto.PeliculaDTO;
import com.alkemy.disney.disney.dto.PeliculaFilterDTO;
import com.alkemy.disney.disney.dto.PersonajeDTO;
import com.alkemy.disney.disney.dto.PersonajeFiltersDTO;
import com.alkemy.disney.disney.entity.GeneroEntity;
import com.alkemy.disney.disney.entity.PeliculaEntity;
import com.alkemy.disney.disney.entity.PersonajeEntity;
import com.alkemy.disney.disney.mapper.PeliculaMapper;
import com.alkemy.disney.disney.mapper.PersonajeMapper;
import com.alkemy.disney.disney.repository.PeliculaRepository;
import com.alkemy.disney.disney.repository.specifications.PeliculaSpecification;
import com.alkemy.disney.disney.service.GeneroService;
import com.alkemy.disney.disney.service.PeliculaService;
import com.alkemy.disney.disney.service.PersonajeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class PeliculaServiceImpl implements PeliculaService {



    @Autowired
    private PeliculaRepository peliculaRepository;

    @Autowired
    private PeliculaMapper peliculaMapper;

    @Autowired
    private GeneroService generoService;

    @Autowired
    private PeliculaSpecification peliculaSpecification;

    @Lazy
    @Autowired
    private PersonajeService personajeService;

    @Override
    public List<PeliculaDTO> getAllPeliculas() {
        List<PeliculaEntity> entidades = peliculaRepository.findAll();

        List<PeliculaDTO> peliculas = peliculaMapper.peliculaEntityList2DTOList(entidades, false);

        return peliculas;
    }

    public PeliculaDTO edit(Long id, PeliculaDTO dto) {
        Optional<PeliculaEntity> encontrada = peliculaRepository.findById(id);
        PeliculaEntity modificada = peliculaMapper.editEntity(encontrada.get(), dto);
        peliculaRepository.save(modificada);
        PeliculaDTO result = peliculaMapper.peliculaEntity2DTO(modificada, false);

        return result;
    }

    public PeliculaDTO save (PeliculaDTO dto) {
        Long generoId = dto.getGeneroId();
        GeneroEntity genero = generoService.buscarEntityPorId(generoId);
        Set<Long> personajesId = dto.getPersonajesId();
        Set<PersonajeEntity> personajes = personajeService.buscarEntitiesPorId(personajesId);
        PeliculaEntity peliculaEntity = peliculaMapper.peliculaDTO2Entity(dto, genero, personajes);
        PeliculaEntity entidadGuardada = peliculaRepository.save(peliculaEntity);
        PeliculaDTO resultado = peliculaMapper.peliculaEntity2DTO(entidadGuardada, true);


        for (PersonajeEntity p: personajes) {
            p.addPelicula(peliculaEntity);
        }
        return resultado;
    }

    @Override
    public void delete(Long id) {
        peliculaRepository.deleteById(id);
    }


    public List<PeliculaDTO> getByFilters(String name, Long genre, String order) {
        PeliculaFilterDTO filtersDTO = new PeliculaFilterDTO(name,genre,order);
        List<PeliculaEntity> entities = peliculaRepository.findAll(peliculaSpecification.getByFilters(filtersDTO));
        List<PeliculaDTO> dtos = peliculaMapper.peliculaEntityList2DTOList(entities, false);

        return dtos;
    }

    @Override
    public PeliculaDTO buscarPorId(Long id) {
        Optional<PeliculaEntity> entity = peliculaRepository.findById(id);
        return peliculaMapper.peliculaEntity2DTO(entity.get(), false);
    }

    @Override
    public PeliculaEntity getEntityById(Long id) {
        return peliculaRepository.getById(id);
    }

    @Override
    public void addPersonaje(Long id, Long idPersonaje) {
        PeliculaEntity entity = peliculaRepository.getById(id);
        entity.getPersonajes().size();
        PersonajeEntity personajeEntity = personajeService.getEntityById(idPersonaje);
        entity.addPersonaje(personajeEntity);
        peliculaRepository.save(entity);
    }

    @Override
    public void removePersonaje(Long id, Long idPersonaje) {
        PeliculaEntity entity = peliculaRepository.getById(id);
        entity.getPersonajes().size();
        PersonajeEntity personajeEntity = personajeService.getEntityById(idPersonaje);
        entity.removePersonaje(personajeEntity);
        peliculaRepository.save(entity);
    }


}
