package com.behnam.spring5recipeapp.services;

import com.behnam.spring5recipeapp.commands.UnitOfMeasureCommand;
import com.behnam.spring5recipeapp.model.UnitOfMeasure;

import java.util.Optional;
import java.util.Set;

public interface UnitOfMeasureService {

    Set<UnitOfMeasureCommand> listAllUoms();
    Optional<UnitOfMeasure> findById(Long id);
}
