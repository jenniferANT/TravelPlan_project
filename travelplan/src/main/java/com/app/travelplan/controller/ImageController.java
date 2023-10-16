package com.app.travelplan.controller;

import com.app.travelplan.model.dto.ImageDto;
import com.app.travelplan.model.entity.Image;
import com.app.travelplan.repository.ImageRepository;
import com.app.travelplan.utils.ImageUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/image")
public class ImageController {
    final private ImageRepository imageRepository;

    public ImageController(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    @PostMapping()
    public ResponseEntity uploadImage(@RequestParam("image") MultipartFile file)
            throws IOException {
        Image image = imageRepository.save(Image.builder()
                .name(file.getOriginalFilename())
                .type(file.getContentType())
                .data(ImageUtils.compressImage(file.getBytes())).build());
        return new ResponseEntity(ImageDto.builder()
                .imageId(image.getId())
                .imageUrl("http://locallhost:8081/api/v1/image/get/"+image.getId())
                .build(),
                HttpStatus.OK);
    }

    @GetMapping(path = {"/get/{id}"})
    public ResponseEntity getImageById(@PathVariable("id") long id) throws IOException {

        final Optional<Image> dbImage = imageRepository.findById(id);
        return ResponseEntity.ok()
                .contentType(MediaType.valueOf(dbImage.get().getType()))
                .body(ImageUtils.decompressImage(dbImage.get().getData()));
    }
}
