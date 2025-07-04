package com.app.calendarbackend.persistence.json.deserializer.impl;

import com.app.calendarbackend.model.Upload;
import com.app.calendarbackend.persistence.entity.UploadsEntity;
import com.app.calendarbackend.persistence.json.converter.JsonConverter;
import com.app.calendarbackend.persistence.json.deserializer.JsonDeserializer;
import com.app.calendarbackend.persistence.json.deserializer.generic.AbstractJsonDeserializer;
import org.springframework.stereotype.Component;

@Component
public class UploadsEntityJsonDeserializer extends AbstractJsonDeserializer<UploadsEntity> implements JsonDeserializer<UploadsEntity> {
    public UploadsEntityJsonDeserializer(JsonConverter<UploadsEntity> converter) {
        super(converter);
    }
}
