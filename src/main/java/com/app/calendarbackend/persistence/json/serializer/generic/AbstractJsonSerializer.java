package com.app.calendarbackend.persistence.json.serializer.generic;

import com.app.calendarbackend.persistence.json.converter.JsonConverter;
import com.app.calendarbackend.persistence.json.serializer.JsonSerializer;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;

import java.io.FileWriter;

// Klasa ta jest abstrakcyjna, ponieważ będzie dostarczała ogólną logikę konwersji z dowolnego typu T
// na dane w formacie JSON, tak że klasy, które będą pochodziły od AbstractJsonSerializer będą musiały
// ustalić tylko z jakim typem pracują, a cała reszta będzie już działała, tak jak określimy w jej ciele.

// Podobnie, jak w przypadku klasy GsonConverter, tutaj również wykorzystaliśmy wzorzec projektowy adapter.
@RequiredArgsConstructor
public abstract class AbstractJsonSerializer<T> implements JsonSerializer<T> {
    private final JsonConverter<T> converter;

    @SneakyThrows
    @Override
    public void toJson(T data, String filename) {
        try (var writer = new FileWriter(filename)) {
            converter.toJson(data, writer);
        }
    }
}
