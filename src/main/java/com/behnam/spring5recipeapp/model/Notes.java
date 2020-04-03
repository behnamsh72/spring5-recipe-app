package com.behnam.spring5recipeapp.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Data
//because we used bidirectional mapping with recipe and category
@EqualsAndHashCode(exclude = {"recipe"})
@Entity
public class Notes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //we didn't use cascade type because if we delete the note we don't want to delete it's recipe
    @OneToOne
    private Recipe recipe;
    //without worrying about limitation size of this notes
    @Lob
    private String recipeNotes;

}
