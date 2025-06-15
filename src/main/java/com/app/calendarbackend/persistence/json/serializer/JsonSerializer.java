package com.app.calendarbackend.persistence.json.serializer;

public interface JsonSerializer<T> {
    void toJson(T data, String filename);
}
