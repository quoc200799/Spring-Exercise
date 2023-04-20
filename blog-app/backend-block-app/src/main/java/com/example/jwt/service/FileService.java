package com.example.jwt.service;

import com.example.jwt.entity.Image;
import com.example.jwt.entity.User;
import com.example.jwt.repository.ImageRepository;
import com.example.jwt.repository.UserRepository;
import com.example.jwt.response.FileResponse;
import com.example.jwt.security.IAuthenticationFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class FileService {
    @Autowired
    private ImageRepository imageRepository;
    @Autowired
    private IAuthenticationFacade authenticationFacade;
    @Autowired
    private UserRepository userRepository;

    public FileResponse uploadFile(MultipartFile file) {
        validateFile(file);
        User user = userRepository.findByEmail(authenticationFacade.getAuthentication().getName()).orElse(null);
        try {
            Image image = Image.builder()
                    .type(file.getContentType())
                    .data(file.getBytes())
                    .url(file.getName())
                    .user(user)
                    .build();
            imageRepository.save(image);

            return new FileResponse("/api/v1/files/" + image.getId());
        } catch (IOException e) {
            throw new RuntimeException("Có lỗi xảy ra");
        }

    }

    private void validateFile(MultipartFile file) {
        String fileName = file.getOriginalFilename();
        // Tên file không được trống
        if(fileName == null || fileName.isEmpty()) {
            throw new RuntimeException("Tên file không hợp lệ");
        }

        // Type file có nằm trong ds cho phép hay không
        // avatar.png, image.jpg => png, jpg
        String fileExtension = getFileExtension(fileName);
        if(!checkFileExtension(fileExtension)) {
            throw new RuntimeException("Type file không hợp lệ");
        }

        // Kích thước size có trong phạm vi cho phép không
        double fileSize = (double) (file.getSize() / 1_048_576);
        if(fileSize > 2) {
            throw new RuntimeException("Size file không được vượt quá 2MB");
        }
    }

    public String getFileExtension(String fileName) {
        int lastIndex = fileName.lastIndexOf(".");
        if(lastIndex == -1) {
            return "";
        }
        return fileName.substring(lastIndex + 1);
    }

    public boolean checkFileExtension(String fileExtension) {
        List<String> fileExtensions = List.of("png", "jpg", "jpeg");
        return fileExtensions.contains(fileExtension.toLowerCase());
    }

    public Image readFile(Integer id) {
        return imageRepository.findById(id)
                .orElseThrow(() -> {
                    throw new RuntimeException("Not found image");
                });
    }
    public String DeleteFileImage(Integer id){
        Image image = imageRepository.findById(id) .orElseThrow(() -> {
            throw new RuntimeException("Not found image");
        });
        imageRepository.delete(image);
        return "Xóa thành công!";
    }
}
