package com.behnam.spring5recipeapp.convertors;

import com.behnam.spring5recipeapp.commands.CategoryCommand;
import com.behnam.spring5recipeapp.model.Category;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import javax.validation.constraints.Null;

@Component
public class CategoryCommandToCategory implements Converter<CategoryCommand, Category> {


    @Synchronized
    @Null
    @Override
    public Category convert(CategoryCommand source) {
        if (source == null)
            return null;
        final Category category = new Category();
        category.setId(source.getId());
        category.setDescription(source.getDescription());

        return category;
    }
}
