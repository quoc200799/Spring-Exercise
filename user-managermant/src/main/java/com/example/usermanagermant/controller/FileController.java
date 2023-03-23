package com.example.usermanagermant.controller;

import com.example.usermanagermant.model.Image;
import com.example.usermanagermant.model.response.FileResponse;
import com.example.usermanagermant.service.FileService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@AllArgsConstructor
@RequestMapping("api/v1")
public class FileController {
    @Autowired
    private final FileService fileService;

    //1. Upload file
    @PostMapping("files")
    public ResponseEntity<?> uploadFile(@ModelAttribute("file") MultipartFile file) {
        FileResponse fileRepository = fileService.uploadFile(file);
        return ResponseEntity.ok(fileRepository);
    }

    //2. Xem thông tin file
    @GetMapping("files/{id}")
    public ResponseEntity<?> readFile(@PathVariable Integer id) {
       Image image = fileService.readFile(id); //mảng các byte dùng để đọc file
        return ResponseEntity
                .ok()
                .contentType(MediaType.parseMediaType(image.getType()))
                .body(image.getData());
    }


}
