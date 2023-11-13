package com.app.travelplan.service.impl;

import com.app.travelplan.model.Sessions;
import com.app.travelplan.model.dto.PlanDto;
import com.app.travelplan.model.entity.*;
import com.app.travelplan.model.form.PlanForm;
import com.app.travelplan.repository.*;
import com.app.travelplan.service.GeneralService;
import com.app.travelplan.service.PlanService;
import com.app.travelplan.utils.AppUtils;
import com.app.travelplan.utils.SecurityUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PlanServiceImpl implements PlanService {
    private final VehicleRepository vehicleRepository;
    private final PlacesRepository placesRepository;
    private final GeneralService generalService;
    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;
    private final PlanRepository planRepository;

    @Override
    public PlanDto generatePlan(PlanForm planForm) {

        if (planForm.getBeginDate().isAfter(planForm.getEndDate())) {
            throw new IllegalArgumentException("Begin date must before end date");
        }
        BigDecimal expense = new BigDecimal(0);

        long distance = 200; //Quãng đường du lịch = km_đi + km_về + km_chơi tại đó
        long estimatedTotalDistance = distance * 2 + 60L; //chênh lêhcj 60km
        Vehicle vehicle = vehicleRepository.findById(planForm.getVehicleId())
                .orElseThrow();
        long timeGo = (long) (((double) distance / vehicle.getAverageSpeed()) * 60); //tổng thời gian di chuyển đến nơi du lịch: đơn vị phút
        long travelTimeMinutes = planForm.getBeginDate().until(planForm.getEndDate(), ChronoUnit.MINUTES); //lấy số phút


        //xét số xe du lịch
        int numberVehicle = (planForm.getNumberPeople() + vehicle.getSeats() - 1) / vehicle.getSeats();
        BigDecimal costVehicle = vehicle.getCost()
                .multiply(BigDecimal
                        .valueOf(estimatedTotalDistance))
                .multiply(BigDecimal
                        .valueOf(numberVehicle))
                .divide(BigDecimal.valueOf(planForm.getNumberPeople()), 0, RoundingMode.HALF_UP);
        BigDecimal cost = planForm.getExpense().subtract(costVehicle);
        if (cost.compareTo(BigDecimal.valueOf(200)) < 0) { //nếu chi phí trên người ít hon 200 thì sẽ tự tăng tiền lên new = 200
            cost = BigDecimal.valueOf(200);
        }

        //thêm tiền chi tiêu trên người
        expense = expense.add(costVehicle);

        final LocalDateTime newEndDate;
        if (travelTimeMinutes - timeGo < 240L) {
            newEndDate = planForm.getBeginDate().plusMinutes(timeGo + 240L); //nếu thời gian du lịch nhỏ hơn 2 tiếng thì tự động cộng thành 2 tiếng
        } else {
            newEndDate = planForm.getEndDate();
        }

        final LocalDateTime newBeginDate = planForm.getBeginDate().plusMinutes(timeGo);//tính thời gian tới và bắt đầu chơi

        List<Places> placesListArea = placesRepository.findAllByCategories_Name(planForm.getDestination());//lấy tất cả địa điểm tại nơi du lịch
        // đêm - ăn sáng - buổi sáng - buổi trưa - buổi chiều - ăn tối - tắm rửa - buổi tối
        List<Sessions> sessions = this.countSessions(newBeginDate, newEndDate); //dếm số buổi khi đi chơi
        List<Places> placesEatList = this.filterByCategoryParent(placesListArea, categoryRepository.findByName("Quán ăn")
                .orElseThrow(() -> new NotFoundException("Category not found with name " + "Quán ăn")));//lọc quán ăn
        for (Places s :
                placesEatList) {
        }
        List<Places> placesDrinkList = this.filterByCategoryParent(placesListArea, categoryRepository.findByName("Quán nước")
                .orElseThrow(() -> new NotFoundException("Category not found with name " + "Quán nuoc")));//lọc quán uống
        BigDecimal costEat = new BigDecimal(0);
        BigDecimal costPlay = new BigDecimal(0);
        List<Category> categories = new ArrayList<>();

        Plan plan = new Plan();
        //-------------------------------chuẩn bị đã xong bắt đầu generate -----------------------------------------------------------
        List<PlanItem> planItems = new ArrayList<>(); //List item plan được chọn
        List<Places> placesReservation = new ArrayList<>(); //List places đã được chọn
        if (planForm.getCategoryId().isEmpty()) {
            //TODO: SAU
        } else {
            Category amThuc = categoryRepository.findByName("Ẩm thực")
                    .orElseThrow(() -> new NotFoundException("Category not found with name " + "Am Thuc"));
            categories = generalService.getAllCategoryByArrayId(planForm.getCategoryId());
            boolean isEat = categories.contains(amThuc); //xem danh mục có ẩm thực
            if (isEat) {
                categories.remove(amThuc);
            }
            //lọc địa điểm: sắp xếp từ lớn đến bé chỉ số yêu thích
            List<Places> placesList = this.filterByListCategories(placesListArea, categories);
            int median = placesList.size() / 2; //trung vị

            //lọc địa điểm ăn
            BigDecimal costAvg = cost.divide(BigDecimal.valueOf(sessions.size()), 0, RoundingMode.HALF_UP);

            int phanLoai = 0;
            if (costAvg.compareTo(BigDecimal.valueOf(500000)) >= 0) {
                phanLoai = 3;
            } else if (costAvg.compareTo(BigDecimal.valueOf(150000)) >= 0) {
                phanLoai = 2;
            } else if (costAvg.compareTo(BigDecimal.valueOf(100000)) >= 0) {
                phanLoai = 1;
            }

            for (Sessions s :
                    sessions) {
            }

            for (Sessions s :
                    sessions) {

                if (s.getIndex() == 1 || s.getIndex() == 3 || s.getIndex() == 6) {

                    LocalDateTime start = newBeginDate.plusMinutes(s.getTimeMinutesBegin());
                    List<Places> placesEatListCopy = this.filterPlacesListGenerate(placesEatList, phanLoai);

                    int length;

                    while (true) {
                        length = placesEatListCopy.size();
                        if (length == 0) throw new IllegalArgumentException("Sorry, cost or data limited");
                        Random random = new Random();
                        int indexRandom = random.nextInt(length);
                        Places places = placesEatListCopy.get(indexRandom);

                        if (!placesReservation.contains(places) && (places.isFull() || (start.toLocalTime().isAfter(places.getBeginDay()) && start.toLocalTime().isBefore(places.getEndDay())))) {

                            if (s.isOptional()) {
                                placesReservation.add(places);
                                planItems.add(PlanItem.builder()
                                        .imageUrl(!places.getImages().isEmpty() ? places.getImages().stream().findFirst().get().getUrlImage() : "https://www.google.com/url?sa=i&url=https%3A%2F%2F3gwifi.net%2Ftravel-la-gi%2F&psig=AOvVaw2oAnTpl2Cc_oltXbBJHLJb&ust=1699943380832000&source=images&cd=vfe&opi=89978449&ved=0CBEQjRxqFwoTCOCUlt2swIIDFQAAAAAdAAAAABAE")
                                        .startTime(start)
                                        .title(places.getTitle())
                                        .content(places.getDescription())
                                        .money(places.getCost())
                                        .note("")
                                        .isOptional(s.isOptional())
                                        .index(s.getIndex())
                                        .placesId(places.getId())
                                        .plan(plan)
                                        .build()
                                );
                                break;
                            } else {
                                if (cost.compareTo(places.getCost()) >= 0) {

                                    cost = cost.subtract(places.getCost()); //trừ cost
                                    expense = expense.add(places.getCost()); //thêm tiền đã dùng
                                    costEat = costEat.add(places.getCost());
                                    placesReservation.add(places);

                                    planItems.add(PlanItem.builder()
                                            .imageUrl(!places.getImages().isEmpty() ? places.getImages().stream().findFirst().get().getUrlImage() : "https://www.google.com/url?sa=i&url=https%3A%2F%2F3gwifi.net%2Ftravel-la-gi%2F&psig=AOvVaw2oAnTpl2Cc_oltXbBJHLJb&ust=1699943380832000&source=images&cd=vfe&opi=89978449&ved=0CBEQjRxqFwoTCOCUlt2swIIDFQAAAAAdAAAAABAE")
                                            .startTime(start)
                                            .title(places.getTitle())
                                            .content(places.getDescription())
                                            .money(places.getCost())
                                            .note("")
                                            .isOptional(s.isOptional())
                                            .index(s.getIndex())
                                            .placesId(places.getId())
                                            .plan(plan)
                                            .build()
                                    );
                                    break;
                                } else {
                                    placesEatListCopy.remove(places);
                                }
                            }
                        } else {
                            placesEatListCopy.remove(places);
                        }
                    }

                }
            }


            //list sessions for đêm
            //list sessions for đi chơi

            //lọc qua từng sessions
            for (Sessions s :
                    sessions) {

                //xử lý từng session
                if (s.getIndex() == 2 || s.getIndex() == 4 || s.getIndex() == 7) {
                    //giờ bắt đầu session
                    LocalDateTime start = newBeginDate.plusMinutes(s.getTimeMinutesBegin());
                    long numberTimeMinutes = s.getNumberTimeMinutes();

                    List<Places> placesEatListCopy = this.filterPlacesListGenerate(placesList, phanLoai);
                    int length;
                    Random random = new Random();

                    while (true) {
                        length = placesEatListCopy.size();
                        if (length == 0) break;

                        Places places = placesEatListCopy.get(random.nextInt(length));

                        if (!placesReservation.contains(places)
                                && numberTimeMinutes >= places.getTimePlaces()
                                && (places.isFull() || (start.toLocalTime().isAfter(places.getBeginDay()) && start.toLocalTime().isBefore(places.getEndDay())))) {
                            if (s.isOptional()) {
                                placesReservation.add(places);
                                planItems.add(PlanItem.builder()
                                        .imageUrl(!places.getImages().isEmpty() ? places.getImages().stream().findFirst().get().getUrlImage() : "https://www.google.com/url?sa=i&url=https%3A%2F%2F3gwifi.net%2Ftravel-la-gi%2F&psig=AOvVaw2oAnTpl2Cc_oltXbBJHLJb&ust=1699943380832000&source=images&cd=vfe&opi=89978449&ved=0CBEQjRxqFwoTCOCUlt2swIIDFQAAAAAdAAAAABAE")
                                        .startTime(start)
                                        .title(places.getTitle())
                                        .content(places.getDescription())
                                        .money(places.getCost())
                                        .note("")
                                        .isOptional(s.isOptional())
                                        .index(s.getIndex())
                                        .placesId(places.getId())
                                        .plan(plan)
                                        .build()
                                );
                                //khi chọn được địa điểm r thì trừ numberTimeMinutes -= (place.gettime +15ph)
                                start = start.plusMinutes(places.getTimePlaces() + 15);
                                numberTimeMinutes = numberTimeMinutes - (places.getTimePlaces() + 15);
                            } else {
                                if (cost.compareTo(places.getCost()) >= 0) {
                                    cost = cost.subtract(places.getCost()); //trừ cost
                                    expense = expense.add(places.getCost()); //thêm tiền đã dùng
                                    costPlay = costPlay.add(places.getCost());
                                    placesReservation.add(places);
                                    planItems.add(PlanItem.builder()
                                            .imageUrl(!places.getImages().isEmpty() ? places.getImages().stream().findFirst().get().getUrlImage() : "https://www.google.com/url?sa=i&url=https%3A%2F%2F3gwifi.net%2Ftravel-la-gi%2F&psig=AOvVaw2oAnTpl2Cc_oltXbBJHLJb&ust=1699943380832000&source=images&cd=vfe&opi=89978449&ved=0CBEQjRxqFwoTCOCUlt2swIIDFQAAAAAdAAAAABAE")
                                            .startTime(start)
                                            .title(places.getTitle())
                                            .content(places.getDescription())
                                            .money(places.getCost())
                                            .note("")
                                            .isOptional(s.isOptional())
                                            .index(s.getIndex())
                                            .placesId(places.getId())
                                            .plan(plan)
                                            .build()
                                    );
                                    start = start.plusMinutes(places.getTimePlaces() + 15);
                                    numberTimeMinutes = numberTimeMinutes - (places.getTimePlaces() + 15);
                                } else {
                                    placesEatListCopy.remove(places);
                                }
                            }
                        } else {
                            placesEatListCopy.remove(places);
                        }
                    }

                }
            }
            //xét địa điểm cuối cùng có trùng free buỏi chiều k
        }

        String username = SecurityUtils.getUsernameOfPrincipal();
        if (username == null) {
            username = "admin";
        }

        plan.setTitle(planForm.getDestination());
        plan.setLocation(Address.builder()
                .latitude(planForm.getLocationLatitude())
                .longitude(planForm.getLocationLongitude())
                .build());
        plan.setDestination(planForm.getDestination());
        plan.setBeginDate(newBeginDate);
        plan.setEndDate(newEndDate);
        plan.setNumberPeople(planForm.getNumberPeople());
        plan.setDistance(distance);
        plan.setEstimatedTotalDistance(estimatedTotalDistance);
        plan.setNumberVehicle(numberVehicle);
        plan.setExpense(expense);
        plan.setCostVehicle(costVehicle);
        plan.setCostEat(costEat);
        plan.setCostPlay(costPlay);
        plan.setPlanItems(planItems);
        plan.setUser(userRepository.findByUsername(username)
                .orElseThrow());
        plan.setCategories(categories);
        plan.setVehicle(vehicle);
        return PlanDto.toDto(planRepository.save(plan));
    }

    @Override
    public void delete(long id) {
        planRepository.deleteById(id);
    }

    @Override
    public List<PlanDto> getAllHistory() {
        User user = userRepository.findByUsername(SecurityUtils.getUsernameOfPrincipal())
                .orElseThrow();

        return user.getPlanList()
                .stream()
                .map(PlanDto::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<PlanDto> getAllCart() {
        User user = userRepository.findByUsername(SecurityUtils.getUsernameOfPrincipal())
                .orElseThrow();
        if(user.getCart()==null) {
            return null;
        }
        return user.getCart()
                .getPlanList()
                .stream()
                .map(PlanDto::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<PlanDto> getAll() {
        return planRepository.findAll()
                .stream()
                .map(PlanDto::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public PlanDto getById(long id) {
        return PlanDto.toDto(
                planRepository.findById(id)
                        .orElseThrow()
        );
    }

    @Override
    public List<PlanDto> getAllShare() {
        User user = userRepository.findByUsername(SecurityUtils.getUsernameOfPrincipal())
                .orElseThrow();

        return null;
    }


    //phân loại địa điểm theo cấp độ
    private List<Places> filterPlacesListGenerate(List<Places> places, int s) {
        String[] phanLoaiArray = new String[]{"Bình dân", "Trung bình", "Cao", "Cao cấp"};
        List<Places> placesList = new ArrayList<>();

        for (int i = 0; i <= s; i++) {
            Category category = categoryRepository.findByName(phanLoaiArray[i]).get();
            for (Places p :
                    places) {
                if (p.getCategories().contains(category)) {
                    placesList.add(p);
                }
            }
        }
        return placesList;
    }

    //lọc tất cả các địa điểm có danh mục thuộc danh mục chung: id like = 1l
    private List<Places> filterByListCategories(List<Places> places, List<Category> categories) {
        List<Places> placesList = new ArrayList<>();

        int value;
        boolean flag;
        long idParent;
        for (Places place : places) {
            value = 0;
            flag = true;
            List<Category> temp = place.getCategories(); //cafe, tra sua,...

            for (Category category : temp) {
                idParent = 0;
                if (category.getCategory() == null) {
                    continue;
                }
                idParent = category.getCategory().getId(); //id of parent
                if (idParent == 4 || idParent == 5) { //4: quan an, 5: quan nuoc
                    flag = false;
                    break;
                }
                //id 1l: like
                if (idParent == 1 && categories.contains(category)) {
                    value++;
                }
            }

            if (!flag || value == 0) {
                continue;
            } else {
                placesList.add(place);
                place.setValue(value);
            }
        }
        // sắp xếp từ lớn đến bé chỉ số yêu thích
        Collections.sort(placesList, Comparator.comparing(Places::getValue).reversed());
        return placesList;
    }

    //Lọc tất cả địa điểm 1 theo danh mục có danh mục nhất định
    private List<Places> filterByCategoryParent(List<Places> places, Category categoryParent) { //category ẩm thực là parent
        List<Places> placesList = new ArrayList<>();
        boolean flag;
        long idParent;

        for (Places place : places) {
            List<Category> temp = place.getCategories();
            flag = false;
            for (Category category : temp) {
                if (category.getCategory() == null) {
                    continue;
                }
                idParent = category.getCategory().getId(); //id of parent

                if (idParent == categoryParent.getId()) { //5l: quan an, 6l: quan nuoc
                    flag = true;
                    break;
                }
            }
            if (flag) {
                placesList.add(place);
            }
        }
        return placesList;
    }

    private List<Sessions> countSessions(LocalDateTime beginDate, LocalDateTime endDate) {
        // đêm - ăn sáng - buổi sáng - buổi trưa - buổi chiều - ăn tối - tắm rửa/free - buổi tối
        List<Sessions> sessions = new ArrayList<>();
        int indexStart = determineTime(beginDate);
        long newTravelTimeMinutes = beginDate.until(endDate, ChronoUnit.MINUTES); //lấy tổng số phút
        LocalTime x = beginDate.toLocalTime();
        long timeMinutesBegin = 0;
        long xToEndPhase;

        for (int i = indexStart; i <= 7; i++) {
            xToEndPhase = Duration.between(x, AppUtils.getTimeInit()[i + 1]).toMinutes();
            if (i == 7) {
                xToEndPhase += 1;
            }

            if (i == 0) {
                //TODO: free
                if (xToEndPhase >= newTravelTimeMinutes) { //hết thời gian du lịch nhưng nếu còn hơn 120 phút thì vẫn qua đêm
                    this.xToEndPhaseMoreThanTravelTimeMinutes(sessions, newTravelTimeMinutes, 120L, xToEndPhase, i, timeMinutesBegin);
                    break;
                } else if (xToEndPhase < 120) {  //bắt đầu thời gian du lịch nhưng nếu còn hơn 120 phút thì vân đi chơi tiếp
                    timeMinutesBegin += xToEndPhase;
                    newTravelTimeMinutes -= xToEndPhase;
                } else { //120 -> max
                    sessions.add(Sessions.builder()
                            .timeMinutesBegin(timeMinutesBegin)
                            .numberTimeMinutes(xToEndPhase)
                            .index(i)
                            .isOptional(false)
                            .build());

                    timeMinutesBegin += xToEndPhase;
                    newTravelTimeMinutes -= xToEndPhase;
                }
            } else if (i == 1) {
                //TODO: ăn sáng
                if (xToEndPhase >= newTravelTimeMinutes) { //optional ăn nếu còn hạn
                    sessions.add(Sessions.builder()
                            .timeMinutesBegin(timeMinutesBegin)
                            .numberTimeMinutes(30)
                            .index(i)
                            .isOptional(true)
                            .build());
                    break;
                } else if (xToEndPhase < 10) {
                    timeMinutesBegin += xToEndPhase;
                    newTravelTimeMinutes -= xToEndPhase;
                } else { //10 -> max
                    sessions.add(Sessions.builder()
                            .timeMinutesBegin(timeMinutesBegin)
                            .numberTimeMinutes(xToEndPhase)
                            .index(i)
                            .isOptional(false)
                            .build());

                    timeMinutesBegin += xToEndPhase;
                    newTravelTimeMinutes -= xToEndPhase;
                }
            } else if (i == 2) {
                //TODO: buổi sáng
                if (xToEndPhase >= newTravelTimeMinutes) { //hết thời gian du lịch nhưng nếu còn hơn 20 phút thì vân đi chơi tiếp
                    this.xToEndPhaseMoreThanTravelTimeMinutes(sessions, newTravelTimeMinutes, 20L, 30L, i, timeMinutesBegin);

                    break;
                } else if (xToEndPhase < 20) { //bắt đầu thời gian du lịch nhưng nếu còn hơn 20 phút thì vân đi chơi tiếp
                    timeMinutesBegin += xToEndPhase;
                    newTravelTimeMinutes -= xToEndPhase;
                } else { //20 -> max
                    sessions.add(Sessions.builder()
                            .timeMinutesBegin(timeMinutesBegin)
                            .numberTimeMinutes(xToEndPhase)
                            .index(i)
                            .isOptional(false)
                            .build());

                    timeMinutesBegin += xToEndPhase;
                    newTravelTimeMinutes -= xToEndPhase;
                }
            } else if (i == 3) {
                //TODO: buổi trưa
                if (xToEndPhase >= newTravelTimeMinutes) { //hết thời gian du lịch nhưng nếu còn hơn 15 phút thì vân đi đi ăn
                    this.xToEndPhaseMoreThanTravelTimeMinutes(sessions, newTravelTimeMinutes, 15L, 30L, i, timeMinutesBegin);
                    break;
                } else if (xToEndPhase < 15) { //bắt đầu thời gian du lịch nhưng nếu còn hơn 15 phút thì vân đi ăn
                    timeMinutesBegin += xToEndPhase;
                    newTravelTimeMinutes -= xToEndPhase;
                } else { //15 -> max
                    sessions.add(Sessions.builder()
                            .timeMinutesBegin(timeMinutesBegin)
                            .numberTimeMinutes(xToEndPhase)
                            .index(i)
                            .isOptional(false)
                            .build());

                    timeMinutesBegin += xToEndPhase;
                    newTravelTimeMinutes -= xToEndPhase;
                }
            } else if (i == 4) {
                //TODO: buổi chiều
                if (xToEndPhase >= newTravelTimeMinutes) { //hết thời gian du lịch nhưng nếu còn hơn 20 phút thì vân đi chơi tiếp
                    this.xToEndPhaseMoreThanTravelTimeMinutes(sessions, newTravelTimeMinutes, 20L, 30L, i, timeMinutesBegin);

                    break;
                } else if (xToEndPhase < 20) { //bắt đầu thời gian du lịch nhưng nếu còn hơn 20 phút thì vân đi chơi tiếp
                    timeMinutesBegin += xToEndPhase;
                    newTravelTimeMinutes -= xToEndPhase;
                } else { //20 -> max
                    sessions.add(Sessions.builder()
                            .timeMinutesBegin(timeMinutesBegin)
                            .numberTimeMinutes(xToEndPhase)
                            .index(i)
                            .isOptional(false)
                            .build());

                    timeMinutesBegin += xToEndPhase;
                    newTravelTimeMinutes -= xToEndPhase;
                }
            } else if (i == 5) {
                //TODO: tắm rửa / free
                if (xToEndPhase >= newTravelTimeMinutes) {
                    if (newTravelTimeMinutes >= 45) {
                        sessions.add(Sessions.builder()
                                .timeMinutesBegin(timeMinutesBegin)
                                .numberTimeMinutes(newTravelTimeMinutes)
                                .index(i)
                                .isOptional(false)
                                .build());

                    } else if (newTravelTimeMinutes >= 15) {
                        sessions.add(Sessions.builder()
                                .timeMinutesBegin(timeMinutesBegin)
                                .numberTimeMinutes(newTravelTimeMinutes)
                                .index(i)
                                .isOptional(true)
                                .build());

                    } else {
                        break;
                    }
                } else {
                    sessions.add(Sessions.builder()
                            .timeMinutesBegin(timeMinutesBegin)
                            .numberTimeMinutes(xToEndPhase)
                            .index(i)
                            .isOptional(false)
                            .build());

                    timeMinutesBegin += xToEndPhase;
                    newTravelTimeMinutes -= xToEndPhase;
                }
            } else if (i == 6) {
                //TODO: ăn tối
                if (xToEndPhase >= newTravelTimeMinutes) { //hết thời gian du lịch nhưng nếu còn hơn 30 phút thì vân đi đi ăn
                    this.xToEndPhaseMoreThanTravelTimeMinutes(sessions, newTravelTimeMinutes, 30L, 30L, i, timeMinutesBegin);
                    break;
                } else if (xToEndPhase < 15) { //bắt đầu thời gian du lịch nhưng nếu còn hơn 15 phút thì vân đi ăn
                    timeMinutesBegin += xToEndPhase;
                    newTravelTimeMinutes -= xToEndPhase;
                } else { //15 -> max
                    sessions.add(Sessions.builder()
                            .timeMinutesBegin(timeMinutesBegin)
                            .numberTimeMinutes(xToEndPhase)
                            .index(i)
                            .isOptional(false)
                            .build());

                    timeMinutesBegin += xToEndPhase;
                    newTravelTimeMinutes -= xToEndPhase;
                }
            } else {
                //TODO: buổi tối
                if (xToEndPhase >= newTravelTimeMinutes) { //hết thời gian du lịch nhưng nếu còn hơn 30 phút thì vân đi đi ăn
                    this.xToEndPhaseMoreThanTravelTimeMinutes(sessions, newTravelTimeMinutes, 30L, 30, i, timeMinutesBegin);

                    break;
                } else if (xToEndPhase < 60) { //bắt đầu thời gian du lịch nhưng nếu còn hơn 15 phút thì vân đi ăn
                    timeMinutesBegin += xToEndPhase;
                    newTravelTimeMinutes -= xToEndPhase;
                } else { //60 -> max
                    sessions.add(Sessions.builder()
                            .timeMinutesBegin(timeMinutesBegin)
                            .numberTimeMinutes(xToEndPhase)
                            .index(i)
                            .isOptional(false)
                            .build());

                    timeMinutesBegin += xToEndPhase;
                    newTravelTimeMinutes -= xToEndPhase;
                }

                x = AppUtils.getTimeInit()[0];
                i = -1;
                continue;
            }

            x = AppUtils.getTimeInit()[i + 1];
        }
        return sessions;
    }

    private void xToEndPhaseMoreThanTravelTimeMinutes(List<Sessions> sessions, long newTravelTimeMinutes, long min, long optionalMinutes, int i, long timeMinutesBegin) {
        if (newTravelTimeMinutes >= min) { //30 -> max
            sessions.add(Sessions.builder()
                    .timeMinutesBegin(timeMinutesBegin)
                    .numberTimeMinutes(newTravelTimeMinutes)
                    .index(i)
                    .isOptional(false)
                    .build());
        } else {
            sessions.add(Sessions.builder()
                    .timeMinutesBegin(timeMinutesBegin)
                    .numberTimeMinutes(optionalMinutes)
                    .index(i)
                    .isOptional(true)
                    .build());
        }
    }

    //xác định lúc đến là thuộc buổi nào
    private int determineTime(LocalDateTime time) {
        LocalTime temp = time.toLocalTime();
        if (temp.isBefore(LocalTime.of(6, 0))) {
            return 0;
        } else if (temp.isBefore(LocalTime.of(8, 0))) {
            return 1;
        } else if (temp.isBefore(LocalTime.of(12, 0))) {
            return 2;
        } else if (temp.isBefore(LocalTime.of(13, 30))) {
            return 3;
        } else if (temp.isBefore(LocalTime.of(17, 30))) {
            return 4;
        } else if (temp.isBefore(LocalTime.of(18, 30))) {
            return 5;
        } else if (temp.isBefore(LocalTime.of(20, 0))) {
            return 6;
        } else {
            return 7;
        }
    }
}