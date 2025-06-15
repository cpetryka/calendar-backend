package com.app.calendarbackend.persistence.json.converter;

import java.io.FileReader;
import java.io.FileWriter;

// Jest to interfejs generyczny. Dzięki temu będziemy mogli dostarczać różne implementacje
// tego interfejsu, w zależności od tego, z jaką biblioteką będziemy pracować.
public interface JsonConverter<T> {
    void toJson(T data, FileWriter writer);
    T fromJson(FileReader reader, Class<T> tClass);
}
