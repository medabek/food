package io.zensoft.food.service.impl;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.PutObjectRequest;
import io.zensoft.food.config.AmazonS3Properties;
import io.zensoft.food.service.AmazonClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

@Slf4j
@Service
public class AmazonClientServiceImpl implements AmazonClient {

    private final AmazonS3Properties amazonS3Properties;

    private final AmazonS3 s3client;

    @Autowired
    public AmazonClientServiceImpl(AmazonS3 s3client, AmazonS3Properties amazonS3Properties) {
        this.s3client = s3client;
        this.amazonS3Properties = amazonS3Properties;
    }

    public File convertMultiPartToFile(MultipartFile file) throws IOException {
        File convertedFile = new File(file.getOriginalFilename());
        try (FileOutputStream fos = new FileOutputStream(convertedFile)) {
            fos.write(file.getBytes());

        }
        return convertedFile;
    }

    public String generateFileName(MultipartFile multiPart) {
        return new Date().getTime() + "-" + multiPart.getOriginalFilename().replace(" ", "_");
    }

    public void uploadFileTos3bucket(String fileName, File file) {
        s3client.putObject(new PutObjectRequest(amazonS3Properties.getBucketName(), fileName, file)
                .withCannedAcl(CannedAccessControlList.PublicRead));
    }

    public String uploadFile(MultipartFile multipartFile) {

        String fileName = "";
        try {
            File file = convertMultiPartToFile(multipartFile);
            fileName = generateFileName(multipartFile);
            uploadFileTos3bucket(fileName, file);
            file.delete();
        } catch (Exception e) {
            log.error("Could not upload this file", e);
        }
        return fileName;
    }

    public void deleteFileFromS3Bucket(String imageName) {
        s3client.deleteObject(new DeleteObjectRequest(amazonS3Properties.getBucketName() + "/", imageName));
    }
}
