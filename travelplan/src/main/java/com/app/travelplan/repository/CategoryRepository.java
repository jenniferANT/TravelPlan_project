package com.app.travelplan.repository;

import com.app.travelplan.model.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    boolean existsByName(String name);
    Optional<Category> findByName(String name);
    void deleteByName(String name);
    List<Category> findAllByCategory_Id(long id);
    List<Category> findAllByCategory_Name(String name);

}
