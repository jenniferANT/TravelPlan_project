package com.app.travelplan.repository;

import com.app.travelplan.model.entity.Link;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LinkRepository extends JpaRepository<Link, Long> {
    boolean existsById(long id);
}
