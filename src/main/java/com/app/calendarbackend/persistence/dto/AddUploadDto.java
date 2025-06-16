package com.app.calendarbackend.persistence.dto;

import java.time.LocalDate;

public record AddUploadDto(LocalDate forDate, String uploadAuthor, String imagePath) {

}
