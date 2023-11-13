package com.app.travelplan.repository;

import com.app.travelplan.model.entity.Follow;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FollowRepository extends JpaRepository<Follow, Long> {
    Optional<Follow> findByUser_Username(String username);
    boolean existsByUser_Username(String username);
}
