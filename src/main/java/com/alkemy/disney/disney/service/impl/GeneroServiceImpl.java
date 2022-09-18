package com.alkemy.disney.disney.service.impl;

import com.alkemy.disney.disney.dto.GeneroDTO;
import com.alkemy.disney.disney.entity.GeneroEntity;
import com.alkemy.disney.disney.mapper.GeneroMapper;
import com.alkemy.disney.disney.repository.GeneroRepository;
import com.alkemy.disney.disney.service.GeneroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GeneroServiceImpl implements GeneroService {

    @Autowired
    private GeneroMapper generoMapper;

    @Autowired
    private GeneroRepository generoRepository;

    public GeneroDTO save(GeneroDTO dto) {
        GeneroEntity entity = generoMapper.generoDTO2Entity(dto);
        GeneroEntity entitySaved = generoRepository.save(entity);
        GeneroDTO generoDTO = generoMapper.generoEntity2DTO(entitySaved);

        return generoDTO;
    }


    public List<GeneroDTO> getAllGeneros() {
        List<GeneroEntity> entities = generoRepository.findAll();
        List<GeneroDTO> result = generoMapper.generoEntity2DTOList(entities);

        return result;
    }

    public GeneroDTO buscarPorId(Long id) {
        Optional<GeneroEntity> entity = generoRepository.findById(id);
        GeneroDTO dto = generoMapper.generoEntity2DTO(entity.get());
        return dto;
    }

    @Override
    public GeneroEntity buscarEntityPorId(Long id) {
        GeneroEntity entity = generoRepository.findById(id).get();
        return entity;
    }

    @Override
    public void delete(Long id) {
        generoRepository.deleteById(id);
    }



}
