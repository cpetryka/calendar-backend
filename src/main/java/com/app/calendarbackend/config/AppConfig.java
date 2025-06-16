package com.app.calendarbackend.config;

import com.app.calendarbackend.model.Upload;
import com.app.calendarbackend.persistence.entity.UploadsEntity;
import com.app.calendarbackend.persistence.json.converter.JsonConverter;
import com.app.calendarbackend.persistence.json.converter.impl.GsonConverter;
import com.app.calendarbackend.persistence.json.deserializer.custom.LocalDateDeserializer;
import com.app.calendarbackend.persistence.json.serializer.custom.LocalDateSerializer;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;

@Configuration
@ComponentScan("com.app.calendarbackend")
public class AppConfig {
    @Bean
    public Gson gson() {
        return new GsonBuilder()
                .registerTypeAdapter(LocalDateTime.class, new LocalDateSerializer())
                .registerTypeAdapter(LocalDateTime.class, new LocalDateDeserializer())
                .setPrettyPrinting()
                .create();
    }
}
