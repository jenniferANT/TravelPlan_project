package com.app.travelplan.repository;

import com.app.travelplan.model.entity.Places;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlacesRepository extends JpaRepository<Places, Long> {
    boolean existsById(long id);
}
