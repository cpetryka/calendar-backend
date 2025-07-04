package com.app.calendarbackend.persistence.converter.upload.impl;

import com.app.calendarbackend.model.Upload;
import com.app.calendarbackend.persistence.converter.upload.FileToUploadsConverter;
import com.app.calendarbackend.persistence.entity.UploadsEntity;
import com.app.calendarbackend.persistence.json.deserializer.JsonDeserializer;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class FileToUploadsConverterImpl implements FileToUploadsConverter {
    private final JsonDeserializer<UploadsEntity> uploadsEntityJsonDeserializer;

    @Override
    public List<Upload> convert(String filename) {
        return uploadsEntityJsonDeserializer
                .fromJson(filename)
                .uploads();
    }
}
