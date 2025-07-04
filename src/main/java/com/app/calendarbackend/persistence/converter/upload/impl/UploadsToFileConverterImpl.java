package com.app.calendarbackend.persistence.converter.upload.impl;

import com.app.calendarbackend.model.Upload;
import com.app.calendarbackend.persistence.converter.upload.UploadsToFileConverter;
import com.app.calendarbackend.persistence.entity.UploadsEntity;
import com.app.calendarbackend.persistence.json.serializer.JsonSerializer;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class UploadsToFileConverterImpl implements UploadsToFileConverter {
    private final JsonSerializer<UploadsEntity> uploadsEntityJsonSerializer;

    @Value("${uploads.filename}")
    private String filename;

    @Override
    public String convert(List<Upload> uploads) {
        uploadsEntityJsonSerializer.toJson(
                new UploadsEntity(uploads),
                filename
        );

        return "";
    }
}
