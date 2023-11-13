package com.app.travelplan.repository;

import com.app.travelplan.model.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findAllByPlaces_Id(long placesId);
}
