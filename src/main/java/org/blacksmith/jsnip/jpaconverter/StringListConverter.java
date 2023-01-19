package org.blacksmith.jsnip.jpaconverter;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Converter
public class StringListConverter implements AttributeConverter<List<String>, String> {
    private static final String SPLIT_CHAR = ";";

    @Override
    public String convertToDatabaseColumn(List<String> value) {
        return Optional.ofNullable(value).map(l -> String.join(SPLIT_CHAR, l)).orElse(null);
    }

    @Override
    public List<String> convertToEntityAttribute(String value) {
        return Optional.ofNullable(value).map(string -> Arrays.asList(string.split(SPLIT_CHAR))).orElse(Collections.emptyList());
    }
}