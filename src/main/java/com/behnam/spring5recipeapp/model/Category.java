package com.behnam.spring5recipeapp.model;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Data
//because we used bidirectional mapping with recipe and category
@EqualsAndHashCode(exclude = {"recipes"})
@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;

    @ManyToMany(mappedBy = "categories")
    private Set<Recipe> recipes;

}
