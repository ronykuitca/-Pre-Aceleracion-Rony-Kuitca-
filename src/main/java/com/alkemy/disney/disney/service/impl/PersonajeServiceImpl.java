package com.alkemy.disney.disney.service.impl;

import com.alkemy.disney.disney.dto.PersonajeDTO;
import com.alkemy.disney.disney.dto.PersonajeFiltersDTO;
import com.alkemy.disney.disney.entity.PeliculaEntity;
import com.alkemy.disney.disney.entity.PersonajeEntity;
import com.alkemy.disney.disney.mapper.PersonajeMapper;
import com.alkemy.disney.disney.repository.PersonajeRepository;
import com.alkemy.disney.disney.repository.specifications.PersonajeSpecification;
import com.alkemy.disney.disney.service.PeliculaService;
import com.alkemy.disney.disney.service.PersonajeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class PersonajeServiceImpl implements PersonajeService {


    @Autowired
    private PersonajeMapper personajeMapper;

    @Autowired
    private PersonajeRepository personajeRepository;

    @Autowired
    private PersonajeSpecification personajeSpecification;

    @Autowired
    @Lazy
    private PeliculaService peliculaService;

    @Override
    public PersonajeDTO edit(Long id, PersonajeDTO dto) {
        Optional<PersonajeEntity> encontrado = personajeRepository.findById(id);

        PersonajeEntity modificada = personajeMapper.editEntity(encontrado.get(), dto);
        personajeRepository.save(modificada);
        PersonajeDTO result = personajeMapper.personajeEntity2DTO(modificada, false);

        return result;
    }

    @Override
    public List<PersonajeDTO> getByFilters(String name, int age, Set<Long> movies, String order) {
        PersonajeFiltersDTO filtersDTO = new PersonajeFiltersDTO(name,age,movies,order);
        List<PersonajeEntity> entities = personajeRepository.findAll(personajeSpecification.getByFilters(filtersDTO));


        List<PersonajeDTO> dtos = personajeMapper.personajeEntityList2DTOList(entities);

        return dtos;
    }

    @Override
    public List<PersonajeDTO> getAllPersonajes() {
        List<PersonajeEntity> entidades = personajeRepository.findAll();

        List<PersonajeDTO> personajes = personajeMapper.personajeEntityList2DTOList(entidades);

        return personajes;
    }

    @Override
    public void delete(Long id) {
        personajeRepository.deleteById(id);
    }

    @Override
    public PersonajeDTO save(PersonajeDTO dto) {
        PersonajeEntity personajeEntity = personajeMapper.personajeDTO2Entity(dto);
        PersonajeEntity entitysaved = personajeRepository.save(personajeEntity);
        PersonajeDTO result = personajeMapper.personajeEntity2DTO(entitysaved, false);

        return result;
    }

    @Override
    public PersonajeDTO buscarPorId(Long id) {
        Optional<PersonajeEntity> entity = personajeRepository.findById(id);

        return personajeMapper.personajeEntity2DTO(entity.get(), false);
    }

    @Override
    public void addPelicula(Long id, Long idPelicula) {
        PersonajeEntity entity = personajeRepository.getById(id);
        entity.getPeliculas().size();
        PeliculaEntity peliculaEntity = peliculaService.getEntityById(idPelicula);
        entity.addPelicula(peliculaEntity);
        personajeRepository.save(entity);
    }

    @Override
    public void removePelicula(Long id, Long idPelicula) {
        PersonajeEntity entity = personajeRepository.getById(id);
        entity.getPeliculas().size();
        PeliculaEntity peliculaEntity = peliculaService.getEntityById(idPelicula);
        entity.removePelicula(peliculaEntity);
        personajeRepository.save(entity);
    }

    public PersonajeEntity getEntityById(Long id) {
        return personajeRepository.findById(id).get();
    }



    public Set<PersonajeEntity> buscarEntitiesPorId(Set<Long> personajesId) {
        Set<PersonajeEntity> personajes = new HashSet<>();
        if (!CollectionUtils.isEmpty(personajesId)) {
            for (Long personajeId : personajesId) {
                Optional<PersonajeEntity> p = personajeRepository.findById(personajeId);
                personajes.add(p.get());
            }
        }
        return personajes;
    }




}
