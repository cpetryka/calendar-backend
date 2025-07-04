package com.app.calendarbackend.dto;

import java.time.LocalDate;
import org.springframework.web.multipart.MultipartFile;

public record UploadFileDto(MultipartFile file, LocalDate forDate, String uploadAuthor) {

}
