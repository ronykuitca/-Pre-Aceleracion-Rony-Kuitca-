package com.alkemy.disney.disney.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Set;

@Getter
@Setter
public class PersonajeFiltersDTO {
    private String name;
    private int age;
    private Set<Long> movies;
    private String order;

    public PersonajeFiltersDTO(String name, int age, Set<Long> movies, String order) {
        this.name = name;
        this.age = age;
        this.movies = movies;
        this.order = order;
    }

    public boolean isASC() {return this.order.compareToIgnoreCase("ASC") ==0;}

    public boolean isDESC() {return this.order.compareToIgnoreCase("DESC") ==0;}
}
