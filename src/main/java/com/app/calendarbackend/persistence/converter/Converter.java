package com.app.calendarbackend.persistence.converter;

public interface Converter<T, U> {
    U convert(T t);
}