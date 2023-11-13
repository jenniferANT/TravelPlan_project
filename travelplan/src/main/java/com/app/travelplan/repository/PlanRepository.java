package com.app.travelplan.repository;

import com.app.travelplan.model.entity.Address;
import com.app.travelplan.model.entity.Plan;
import com.app.travelplan.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlanRepository extends JpaRepository<Plan, Long> {

    List<Plan> findAllByUser(User user);
}
