package com.app.calendarbackend.persistence.converter.upload;

import com.app.calendarbackend.model.Upload;
import com.app.calendarbackend.persistence.converter.Converter;
import com.app.calendarbackend.persistence.entity.UploadsEntity;

import java.util.List;

public interface UploadsToFileConverter extends Converter<List<Upload>, String> {

}
