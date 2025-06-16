package com.app.calendarbackend.persistence.json.converter.impl;

import com.app.calendarbackend.persistence.json.converter.JsonConverter;
import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.FileReader;
import java.io.FileWriter;

// Wykorzystujemy tu tak naprawdę wzorzec adapter - adaptujemy obiekt klasy Gson do pracy
// z naszym interfejsem JsonConverter, który będziemy wykorzystywać dalej w naszej aplikacji.
@Component
@RequiredArgsConstructor
public class GsonConverter<T> implements JsonConverter<T> {
    private final Gson gson;

    @Override
    public void toJson(T data, FileWriter writer) {
        gson.toJson(data, writer);
    }

    @Override
    public T fromJson(FileReader reader, Class<T> tClass) {
        return gson.fromJson(reader, tClass);
    }
}
