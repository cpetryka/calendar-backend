package com.app.calendarbackend.persistence.json.deserializer;

public interface JsonDeserializer<T> {
    T fromJson(String filename);
}
