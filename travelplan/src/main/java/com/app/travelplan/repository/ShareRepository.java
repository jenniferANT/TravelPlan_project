package com.app.travelplan.repository;

import com.app.travelplan.model.entity.Plan;
import com.app.travelplan.model.entity.Share;
import com.app.travelplan.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ShareRepository extends JpaRepository<Share, Long> {
    List<Share> findAllByRemitter_Username(String username);

    List<Share> findAllByReceiverContains(User user);

    void deleteAllByPlan(Plan plan);
}
