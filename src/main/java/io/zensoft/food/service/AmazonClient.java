package io.zensoft.food.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

public interface AmazonClient {
    File convertMultiPartToFile(MultipartFile file) throws IOException;

    String generateFileName(MultipartFile multiPart);

    void uploadFileTos3bucket(String fileName, File file);

    String uploadFile(MultipartFile multipartFile);

    void deleteFileFromS3Bucket(String fileUrl);
}