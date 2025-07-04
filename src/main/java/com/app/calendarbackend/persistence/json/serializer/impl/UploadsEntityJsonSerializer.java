package com.app.calendarbackend.persistence.json.serializer.impl;

import com.app.calendarbackend.model.Upload;
import com.app.calendarbackend.persistence.entity.UploadsEntity;
import com.app.calendarbackend.persistence.json.converter.JsonConverter;
import com.app.calendarbackend.persistence.json.serializer.JsonSerializer;
import com.app.calendarbackend.persistence.json.serializer.generic.AbstractJsonSerializer;
import org.springframework.stereotype.Component;

@Component
public final class UploadsEntityJsonSerializer extends AbstractJsonSerializer<UploadsEntity> implements JsonSerializer<UploadsEntity> {
    public UploadsEntityJsonSerializer(JsonConverter<UploadsEntity> converter) {
        super(converter);
    }
}
