package com.example.usermanagermant.service;

import com.example.usermanagermant.exception.NotFoundException;
import com.example.usermanagermant.model.Image;
import com.example.usermanagermant.model.response.FileResponse;
import com.example.usermanagermant.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

//import static org.apache.catalina.startup.ClassLoaderFactory.validateFile;

@Service
public class FileService {
    @Autowired
    private ImageRepository imageRepository;

    //    private final Path rootPath = Paths.get("uploads");
//    public FileService(){
//        createFolder(rootPath.toString());
//    }
//    private void createFolder(String path) { // tạo folder
//        File file = new File(path);
//        if (!file.exists()) { // nếu có rồi sẽ bỏ qua
//            file.mkdirs();
//        }
//    }
    public FileResponse uploadFile(MultipartFile file) {
        validateFile(file);
        try {
            Image image = Image.builder()
                    .type(file.getContentType())
                    .data(file.getBytes())
                    .createAt(LocalDateTime.now())
                    .build();
            imageRepository.save(image);
            return new FileResponse("/api/v1/files/" + image.getId());
        } catch (IOException e) {
            throw new RuntimeException("Có lỗi xảy ra!");
        }

//        String fileId = UUID.randomUUID().toString();
//        Path filePath = rootPath.resolve(fileId);
//        File newFile = filePath.toFile();
//        try (OutputStream outputStream = new FileOutputStream(newFile)) {
//            outputStream.write(file.getBytes());
//            return new FileResponse("/api/v1/files/" + fileId);
//
//        } catch (IOException e) {
//            throw new RuntimeException("Loi trong qua trinh upload fike");
//        }
        // TODO : kiếm tra xem sao lại không được
//        try {
//            file.transferTo(newFile);
//            return new FileRepository("/api/v1/files/" + fileId);
//        } catch (IOException e) {
//            throw new RuntimeException("Loi trong qua trinh upload fike");
//        }
    }

    private void validateFile(MultipartFile file) {
        String fileName = file.getOriginalFilename(); // chuyển muitipartfile thành string
        //Tên file không được để trống
        if (fileName == null || fileName.isEmpty()) {
            throw new NotFoundException("File not found");
        }
        //Type file có nằm trong ds cho phép hay không?
        // avatar.png, image.jpg => png, jpg
        String fileExtension = getFileExtension(fileName);
        if (!checkFileExtension(fileExtension)) {
            throw new NotFoundException("Type File không hợp lệ");
        }
        //Kihchs thước size có trong phạm vi cho phép hay không
        double fileSize = (double) file.getSize() / 1048576;
        if (fileSize > 2) {
            throw new NotFoundException("Size file không được vượt quá 2MB");

        }
    }

    public String getFileExtension(String fileName) {
        int lastIndex = fileName.lastIndexOf(".");
        if (lastIndex == -1) {
            return "";
        }
        return fileName.substring(lastIndex + 1);
    }

    public boolean checkFileExtension(String fileExtension) {
        List<String> fileExtensions = List.of("png", "jpg", "jpeg");
        return fileExtensions.contains(fileExtension.toLowerCase());
    }

    public Image readFile(Integer id) {
//        Path filePath = rootPath.resolve(id);
//        File file = new File(filePath.toString());

//        if (!file.exists()) { // kiểm tra tìm thấy file hay ko
//            throw new NotFoundException("Not Found file id " + id);
//        }
//        try {
//            return Files.readAllBytes(filePath);
//        } catch (IOException e) {
//            throw new RuntimeException("Loi trong qua trinh read file");
//        }
        Image image = imageRepository.findById(id)
                .orElseThrow(()->{
                     throw new NotFoundException("Không tìm thấy file");
                });
        return image;
    }
}
