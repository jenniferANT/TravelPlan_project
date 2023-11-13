package com.app.travelplan.repository;

import com.app.travelplan.model.entity.Address;
import com.app.travelplan.model.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart, Long> {
    boolean existsByUser_Username(String username);
    Optional<Cart> findByUser_Username(String username);


}
