package com.example.jwt.controller;

import com.example.jwt.entity.Image;
import com.example.jwt.response.FileResponse;
import com.example.jwt.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("api/v1/files")
public class FileController {
    @Autowired
    private FileService fileService;

    // 1. Upload file
    @Secured({"ROLE_USER"})
    @PostMapping("files")
    public ResponseEntity<?> uploadFile(@ModelAttribute("file") MultipartFile file) {
        FileResponse fileResponse = fileService.uploadFile(file);
        return ResponseEntity.ok(fileResponse);
    }

    // 2. Xem thông tin file
    @Secured({"ROLE_USER"})
    @GetMapping(value = "files/{id}")
    public ResponseEntity<?> readFile(@PathVariable Integer id) {
        Image image = fileService.readFile(id);
        return ResponseEntity
                .ok()
                .contentType(MediaType.parseMediaType(image.getType()))
                .body(image.getData());
    }
    @Secured({"ROLE_USER"})
    @DeleteMapping(value = "files/{id}")
    public ResponseEntity<?> deleteFile(@PathVariable Integer id) {
        String message = fileService.DeleteFileImage(id);
        return ResponseEntity
                .ok()
                .body(message);
    }
}
