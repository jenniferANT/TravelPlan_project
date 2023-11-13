package com.app.travelplan.repository;

import com.app.travelplan.model.entity.Address;
import com.app.travelplan.model.entity.PlanItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlanItemRepository extends JpaRepository<PlanItem, Long> {
}
