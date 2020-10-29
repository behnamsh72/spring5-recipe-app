package com.behnam.spring5recipeapp.repositories;

import com.behnam.spring5recipeapp.model.Notes;
import org.springframework.data.repository.CrudRepository;

public interface NotesRepository extends CrudRepository<Notes, Long> {
}
