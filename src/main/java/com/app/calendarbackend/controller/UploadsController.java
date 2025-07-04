package com.app.calendarbackend.controller;

import com.app.calendarbackend.dto.ResponseDto;
import com.app.calendarbackend.dto.UploadFileDto;
import com.app.calendarbackend.model.Upload;
import com.app.calendarbackend.dto.AddUploadDto;
import com.app.calendarbackend.persistence.repository.UploadRepository;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/uploads")
@RequiredArgsConstructor
public class UploadsController {
    private final UploadRepository uploadRepository;

    /**
     * Endpoint to check if an upload exists for a specific date.
     * GET /api/uploads/exists?date=YYYY-MM-DD
     * @param date The date to check for, in YYYY-MM-DD format.
     * @return ResponseDto with true/false indicating existence.
     */
    @GetMapping("/exists")
    public ResponseDto<Boolean> checkIfUploadExists(@RequestParam("date") LocalDate date) {
        return new ResponseDto<>(uploadRepository.containsUpload(date));
    }

    /**
     * Endpoint to add a new upload.
     * POST /api/uploads
     * Request Body: { "forDate": "YYYY-MM-DD", "uploadAuthor": "Author Name", "imagePath": "/path/to/image.jpg" }
     * @param addUploadDto DTO containing upload details.
     * @return ResponseEntity with ResponseDto indicating success or error.
     */
    @PostMapping
    public ResponseEntity<ResponseDto<String>> addUpload(@RequestBody AddUploadDto addUploadDto) {
        if (uploadRepository.containsUpload(addUploadDto.forDate())) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(new ResponseDto<>("Upload already exists for date: " + addUploadDto.forDate()));
        }

        try {
            uploadRepository.addUpload(addUploadDto);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(new ResponseDto<>("Upload added successfully for date: " + addUploadDto.forDate(), null));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseDto<>("Failed to add upload: " + e.getMessage()));
        }
    }

    /**
     * Endpoint to upload a file.
     * POST /api/uploads/uploadFile
     * Request Body: { "file": <file>, "forDate": "YYYY-MM-DD", "uploadAuthor": "Author Name" }
     * @param uploadFileDto DTO containing file and metadata.
     * @return ResponseEntity with ResponseDto indicating success or error.
     */
    @PostMapping("/uploadFile")
    public ResponseEntity<ResponseDto<String>> uploadFile(
        @RequestBody UploadFileDto uploadFileDto) {

        if (uploadRepository.containsUpload(uploadFileDto.forDate())) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(new ResponseDto<>("Upload already exists for date: " + uploadFileDto.forDate()));
        }

        try {
            String uploadDir = "uploads/";
            Files.createDirectories(Paths.get(uploadDir)); // Create a folder if it doesn't exist
            String filename = uploadFileDto.file().getOriginalFilename();
            Path filepath = Paths.get(uploadDir, filename);
            Files.write(filepath, uploadFileDto.file().getBytes());

            // Save to repository
            uploadRepository.addUpload(new AddUploadDto(uploadFileDto.forDate(), uploadFileDto.uploadAuthor(), filepath.toString()));

            return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ResponseDto<>("File uploaded and saved successfully for date: " + uploadFileDto.forDate()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ResponseDto<>("Failed to upload file: " + e.getMessage()));
        }
    }

    /**
     * Endpoint to get all uploads.
     * GET /api/uploads
     * @return ResponseEntity with ResponseDto containing a List of Upload objects, or an error.
     */
    @GetMapping
    public ResponseEntity<ResponseDto<List<Upload>>> getAllUploads() {
        List<Upload> uploads = uploadRepository.getAllUploads();
        if (uploads.isEmpty()) {
            return ResponseEntity.ok(new ResponseDto<>(uploads));
        }
        return ResponseEntity.ok(new ResponseDto<>(uploads, null));
    }

    /**
     * Endpoint to get uploads for a specific date.
     * GET /api/uploads/byDate?date=YYYY-MM-DD
     * @param date The date to filter uploads by.
     * @return ResponseEntity with ResponseDto containing a List of Upload objects, or an error.
     */
    @GetMapping("/byDate")
    public ResponseEntity<ResponseDto<List<Upload>>> getUploadsForDate(@RequestParam("date") LocalDate date) {
        List<Upload> uploads = uploadRepository.getUploadFor(date);
        if (uploads.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ResponseDto<>("No uploads found for date: " + date));
        }
        return ResponseEntity.ok(new ResponseDto<>(uploads, null));
    }

    /**
     * Endpoint to remove all uploads for a specific date.
     * DELETE /api/uploads?date=YYYY-MM-DD
     * @param date The date for which to remove uploads.
     * @return ResponseEntity with ResponseDto indicating success or error.
     */
    @DeleteMapping
    public ResponseEntity<ResponseDto<String>> removeUploadsForDate(@RequestParam("date") LocalDate date) {
        if (!uploadRepository.containsUpload(date)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ResponseDto<>("No uploads found for date: " + date + " to remove."));
        }

        try {
            uploadRepository.removeUploadForDate(date);
            return ResponseEntity.ok(new ResponseDto<>("Uploads for date " + date + " removed successfully.", null));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseDto<>("Failed to remove uploads for date " + date + ": " + e.getMessage()));
        }
    }
}