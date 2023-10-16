package com.app.travelplan.repository;

import com.app.travelplan.model.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ImageRepository extends JpaRepository<Image, Long> {
    boolean existsImageById(Long id);
}
