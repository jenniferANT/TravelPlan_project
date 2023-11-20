package com.app.travelplan.repository;

import com.app.travelplan.model.entity.Follow;
import com.app.travelplan.model.entity.Places;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlacesRepository extends JpaRepository<Places, Long> {
    boolean existsById(long id);
    List<Places> findAllByFollows(Follow follow);
    List<Places> findAllByCategories_Name(String name);
}
