package com.alkemy.disney.disney.dto;

import com.alkemy.disney.disney.entity.PeliculaEntity;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class PersonajeDTO {
    private Long id;
    private String imagen;
    private String nombre;
    private int edad;
    private double peso;
    private String historia;
    List<PeliculaDTO> peliculas;
}
