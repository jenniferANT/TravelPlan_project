package com.app.travelplan.service;

import com.app.travelplan.model.dto.ListResponse;
import com.app.travelplan.model.dto.PlanDto;
import com.app.travelplan.model.entity.Plan;
import com.app.travelplan.model.form.PlanForm;

import java.util.List;

public interface PlanService {
    PlanDto generatePlan(PlanForm planForm);

    String delete(long id);

    //lấy lịch sử
    List<PlanDto> getAllHistory();

    //lấy danh sách plan đã lưu
    ListResponse getAll(int pageNo, int pageSize, String sortBy, String sortDir);
    PlanDto getById(long id);

    //lấy danh sách plan đã share
    List<PlanDto> getAllShare();
    List<PlanDto> getAllCart();
}
