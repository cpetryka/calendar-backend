package com.app.calendarbackend.persistence.json.deserializer.generic;

import com.app.calendarbackend.persistence.json.converter.JsonConverter;
import com.app.calendarbackend.persistence.json.deserializer.JsonDeserializer;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;

import java.io.FileReader;
import java.lang.reflect.ParameterizedType;

@RequiredArgsConstructor
public abstract class AbstractJsonDeserializer<T> implements JsonDeserializer<T> {
    private final JsonConverter<T> converter;

    @SuppressWarnings("unchecked")
    private final Class<T> type =
            (Class<T>) ((ParameterizedType)getClass().getGenericSuperclass()).getActualTypeArguments()[0];

    @SneakyThrows
    @Override
    public T fromJson(String filename) {
        try (var reader = new FileReader(filename)) {
            return converter.fromJson(reader, type);
        }
    }
}
