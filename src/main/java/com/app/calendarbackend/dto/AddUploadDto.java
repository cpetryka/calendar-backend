package com.app.calendarbackend.dto;

import java.time.LocalDate;

public record AddUploadDto(LocalDate forDate, String uploadAuthor, String imagePath) {

}
