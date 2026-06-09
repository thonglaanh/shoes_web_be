package com.shoes.controller;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@RestController
@RequestMapping("/api/upload")
@CrossOrigin(origins = "*")
public class UploadController {

    @Value("dmyeyw6do")
    private String cloudName;

    @Value("373226947945326")
    private String apiKey;

    @Value("rsL30mFPTr919FHlcpXqinxc4H0")
    private String apiSecret;

    @PostMapping
    public ResponseEntity<?> upload(
            @RequestParam("file") MultipartFile file) {

        try {

            Cloudinary cloudinary = new Cloudinary(
                    ObjectUtils.asMap(
                            "cloud_name", cloudName,
                            "api_key", apiKey,
                            "api_secret", apiSecret));

            Map<?, ?> result = cloudinary.uploader().upload(
                    file.getBytes(),
                    ObjectUtils.emptyMap());

            String imageUrl = result.get("secure_url").toString();

            return ResponseEntity.ok(
                    Map.of(
                            "url", imageUrl));

        } catch (Exception e) {

            return ResponseEntity.internalServerError()
                    .body(
                            Map.of(
                                    "error",
                                    e.getMessage()));
        }
    }
}