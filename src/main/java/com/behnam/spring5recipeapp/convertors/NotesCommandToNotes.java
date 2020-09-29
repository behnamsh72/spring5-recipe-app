package com.behnam.spring5recipeapp.convertors;

import com.behnam.spring5recipeapp.commands.NotesCommand;
import com.behnam.spring5recipeapp.model.Notes;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class NotesCommandToNotes implements Converter<NotesCommand, Notes> {
    @Override
    public Notes convert(NotesCommand notesCommand) {
        return null;
    }
}
