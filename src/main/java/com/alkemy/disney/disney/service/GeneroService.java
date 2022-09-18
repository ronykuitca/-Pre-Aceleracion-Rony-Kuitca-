package com.alkemy.disney.disney.service;

import com.alkemy.disney.disney.dto.GeneroDTO;
import com.alkemy.disney.disney.entity.GeneroEntity;

import java.util.List;

public interface GeneroService {
    GeneroDTO save (GeneroDTO dto);

    List<GeneroDTO> getAllGeneros();

    GeneroDTO buscarPorId (Long generoId);

    GeneroEntity buscarEntityPorId (Long id);

    void delete(Long id);
}
