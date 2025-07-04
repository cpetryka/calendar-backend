package com.app.calendarbackend;

import com.app.calendarbackend.persistence.json.deserializer.custom.LocalDateDeserializer;
import com.app.calendarbackend.persistence.json.serializer.custom.LocalDateSerializer;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.time.LocalDate;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@SpringBootApplication
public class CalendarBackendApplication {
    @Bean
    public Gson gson() {
        return new GsonBuilder()
            .registerTypeAdapter(LocalDate.class, new LocalDateSerializer())
            .registerTypeAdapter(LocalDate.class, new LocalDateDeserializer())
            .setPrettyPrinting()
            .create();
    }

    public static void main(String[] args) {
        SpringApplication.run(CalendarBackendApplication.class, args);
    }

}
