package com.behnam.spring5recipeapp.convertors;

import com.behnam.spring5recipeapp.commands.CategoryCommand;
import com.behnam.spring5recipeapp.model.Category;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CategoryCommandToCategory  implements Converter<CategoryCommand, Category> {
    @Override
    public Category convert(CategoryCommand categoryCommand) {
        return null;
    }
}
