package com.app.travelplan.controller;

import com.app.travelplan.model.dto.ImageDto;
import com.app.travelplan.model.entity.Image;
import com.app.travelplan.repository.ImageRepository;
import com.app.travelplan.utils.ImageUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Encoding;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class ImageController {
    final private ImageRepository imageRepository;

    public ImageController(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    @PostMapping(value = "/api/v1/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity uploadImage(@RequestParam("image") MultipartFile file)
            throws IOException {
        Image image = imageRepository.save(Image.builder()
                .name(file.getOriginalFilename())
                .type(file.getContentType())
                .data(ImageUtils.compressImage(file.getBytes())).build());
        return new ResponseEntity(ImageDto.toDto(image), HttpStatus.OK);
    }

    @GetMapping(path = {"/api/v1/image/get/{id}"})
    public ResponseEntity getImageById(@PathVariable("id") long id) throws IOException {

        final Optional<Image> dbImage = imageRepository.findById(id);
        return ResponseEntity.ok()
                .contentType(MediaType.valueOf(dbImage.get().getType()))
                .body(ImageUtils.decompressImage(dbImage.get().getData()));
    }

    @PostMapping("/api/v1/admin/image/clear")
    public ResponseEntity clearImage() {
        List<Image> images = imageRepository.findAll();
        for (Image i :
                images) {
            if (i.getUser() != null || i.getPlaces() != null){
                continue;
            }
            imageRepository.delete(i);
        }
        return new ResponseEntity<>("Success", HttpStatus.OK);
    }

    @GetMapping("/api/v1/admin/image/get-all")
    public ResponseEntity getAll() {
        return new ResponseEntity(imageRepository.findAll()
                .stream()
                .map(ImageDto::toDto)
                .collect(Collectors.toList())
        , HttpStatus.OK);
    }
}
