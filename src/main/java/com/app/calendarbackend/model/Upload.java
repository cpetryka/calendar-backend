package com.app.calendarbackend.model;

import java.time.LocalDate;

public record Upload(LocalDate forDate, LocalDate uploadDate, String uploadAuthor, String imagePath) {
}
