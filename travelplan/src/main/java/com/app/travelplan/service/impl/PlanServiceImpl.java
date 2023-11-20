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
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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
@Transactional
public class PlanServiceImpl implements PlanService {
    private final VehicleRepository vehicleRepository;
    private final PlacesRepository placesRepository;
    private final GeneralService generalService;
    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;
    private final PlanRepository planRepository;
    private final ShareRepository shareRepository;
    private final CartRepository cartRepository;

    @Override
    public String delete(long id) {
        Plan plan = planRepository.findById(id)
                .orElseThrow(() ->
                        new IllegalArgumentException("Plan not found with id " + id));

        //TODO: xóa plan trong cart
        if (SecurityUtils.getRoleOfPrincipal().equals("ROLE_ADMIN") ||
                plan.getUser().getUsername().equals(SecurityUtils.getUsernameOfPrincipal())) {
            shareRepository.deleteAllByPlan(plan);
            planRepository.deleteById(id);
            return "Success";
        }

        throw new IllegalArgumentException("Not permit");
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
        if (user.getCart() == null) {
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
                        .orElseThrow(() ->
                                new IllegalArgumentException("Plan not found with id " + id))
        );
    }

    @Override
    public List<PlanDto> getAllShare() {
        User user = userRepository.findByUsername(SecurityUtils.getUsernameOfPrincipal())
                .orElseThrow();

        return null;
    }

    @Override
    public PlanDto generatePlan(PlanForm planForm) {
        if (planForm.getBeginDate().isAfter(planForm.getEndDate())) {
            throw new IllegalArgumentException("Begin date must before end date");
        }

        BigDecimal expenseOnePeople, cost;
        long distance, numberDate, estimatedTotalDistance, timeGo, numberVehicle, travelTimeMinutes;
        LocalDateTime newBeginDate, newEndDate;

        expenseOnePeople = new BigDecimal(0); //chi phí đã dùng trên 1 người
        cost = planForm.getExpense(); //chi phi input trên người

        Vehicle vehicle = vehicleRepository.findById(planForm.getVehicleId())
                .orElseThrow(() ->
                        new IllegalArgumentException("Vehicle not found with id " + planForm.getVehicleId()));

        distance = 100; //khoảng cách du lịch
        numberDate = planForm.getBeginDate().until(planForm.getEndDate(), ChronoUnit.DAYS) + 1;
        estimatedTotalDistance = distance * 2 * 130 / 100 + 50 * numberDate; //trung binh 50km/ngay
        timeGo = (long) (((double) distance / vehicle.getAverageSpeed()) * 1.3 * 60.0); //thời gian du lịch đến đó: độ trễ là 1.3 lần
        numberVehicle = (planForm.getNumberPeople() + vehicle.getSeats() - 1) / vehicle.getSeats();

        BigDecimal costVehicle = vehicle.getCost() //chi phí phương tiện trên người
                .multiply(BigDecimal
                        .valueOf(estimatedTotalDistance))
                .multiply(BigDecimal
                        .valueOf(numberVehicle))
                .divide(BigDecimal.valueOf(planForm.getNumberPeople()), 0, RoundingMode.HALF_UP);
        expenseOnePeople = expenseOnePeople.add(costVehicle);

        //kiểm tra chi phí
        cost = cost.subtract(costVehicle);
        if (cost.compareTo(BigDecimal.valueOf(200)) < 0) {
            cost = BigDecimal.valueOf(200);
        }

        newBeginDate = planForm.getBeginDate().plusMinutes(timeGo); //thời gian bắt đầu chơi = input + thời gian di chuyển
        newEndDate = planForm.getEndDate();
        travelTimeMinutes = newBeginDate.until(newEndDate, ChronoUnit.MINUTES); //thời gian du lịch
        if (travelTimeMinutes < 240L) { //thời gian du lịch luôn lớn hơn 4 tiếng
            newEndDate = newBeginDate.plusMinutes(240L);
        }

        //lấy tất cả địa điểm tại nơi du lịch: Vũng Tàu
        List<Places> placesListArea = placesRepository.findAllByCategories_Name(planForm.getDestination());

        List<Places> placesEatList = filterByCategoryParent(placesListArea, categoryRepository.findByName("Quán ăn")
                .orElseThrow(() -> new NotFoundException("Category not found with name " + "Quán ăn")));
        BigDecimal costEat = new BigDecimal(0);
        BigDecimal costPlay = new BigDecimal(0);

        List<Sessions> sessions = countSessionsUpdate(newBeginDate, travelTimeMinutes);
        Plan plan = new Plan();

        //-------------------------------chuẩn bị đã xong bắt đầu generate -----------------------------------------------------------
        List<Category> categories = null;
        if (!planForm.getCategoryId().isEmpty()) {
            Category amThuc = categoryRepository.findByName("Ẩm thực")
                    .orElseThrow(() -> new NotFoundException("Category not found with name " + "Am Thuc"));
            categories = generalService.getAllCategoryByArrayId(planForm.getCategoryId());
            boolean isEat = categories.contains(amThuc); //xem danh mục có ẩm thực
            if (isEat) {
                categories.remove(amThuc);
            }

            placesListArea = filterByListCategories(placesListArea, categories);
        }

        //lọc địa điểm ăn
        BigDecimal costAvg = cost.divide(BigDecimal.valueOf(sessions.size()), 0, RoundingMode.HALF_UP);

        String[] phanLoaiArray = new String[]{"Tiết kiệm", "Bình dân", "Vừa", "Cao", "Cao cấp"};
        int phanLoai;
        if (costAvg.compareTo(BigDecimal.valueOf(300000)) >= 0) {
            phanLoai = 4;
        } else if (costAvg.compareTo(BigDecimal.valueOf(100000)) >= 0) {
            phanLoai = 3;
        } else if (costAvg.compareTo(BigDecimal.valueOf(30000)) >= 0) {
            phanLoai = 2;
        } else if (costAvg.compareTo(BigDecimal.valueOf(20000)) >= 0) {
            phanLoai = 1;
        } else {
            phanLoai = 0;
        }

        //List item plan được chọn
        List<PlanItem> planItems = new ArrayList<>();
        placesEatList = this.filterPlacesListGenerate(placesEatList, phanLoai);
        for (Sessions s :
                sessions) {
            if (s.getIndex() == 1 || s.getIndex() == 3 || s.getIndex() == 6) {

                LocalDateTime start = newBeginDate.plusMinutes(s.getTimeMinutesBegin());
                List<Places> placesEatListCopy = new ArrayList<Places>(placesEatList);
                int length;

                while (true) {
                    length = placesEatListCopy.size();
                    if (length == 0) throw new IllegalArgumentException("Sorry, cost or data limited");
                    Random random = new Random();
                    Places places = placesEatListCopy.get(random.nextInt(length));

                    if (places.isFull() ||
                            (start.toLocalTime().isAfter(places.getBeginDay()) && start.toLocalTime().isBefore(places.getEndDay()))) {

                        if (s.isOptional()) {
                            planItems.add(PlanItem.builder()
                                    .imageUrl(!places.getImages().isEmpty()
                                            ? places.getImages().stream().findFirst().get().getUrlImage()
                                            : "https://www.google.com/url?sa=i&url=https%3A%2F%2F3gwifi.net%2Ftravel-la-gi%2F&psig=AOvVaw2oAnTpl2Cc_oltXbBJHLJb&ust=1699943380832000&source=images&cd=vfe&opi=89978449&ved=0CBEQjRxqFwoTCOCUlt2swIIDFQAAAAAdAAAAABAE")
                                    .startTime(start)
                                    .title(places.getTitle())
                                    .content(places.getDescription())
                                    .money(places.getCost())
                                    .note("")
                                    .isOptional(s.isOptional())
                                    .index(s.getIndex())
                                    .placesId(places.getId())
                                    .plan(plan)
                                    .timeGoTwoPlaces(0)
                                    .build()
                            );
                            placesEatList.remove(places);
                            break;
                        } else {
                            if (cost.compareTo(places.getCost()) >= 0) {

                                cost = cost.subtract(places.getCost()); //trừ cost
                                expenseOnePeople = expenseOnePeople.add(places.getCost()); //thêm tiền đã dùng
                                costEat = costEat.add(places.getCost());

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
                                        .timeGoTwoPlaces(0)
                                        .build()
                                );
                                placesEatList.remove(places);
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

        placesListArea = filterPlacesListGenerate(placesListArea, phanLoai);
        for (Sessions s :
                sessions) {
            Places placesBefore = null;

            if (s.getIndex() == 2 || s.getIndex() == 4 || s.getIndex() == 7 || (s.getIndex() == 5 && s.isOptional())) {
                LocalDateTime start = newBeginDate.plusMinutes(s.getTimeMinutesBegin());
                long numberTimeMinutes = s.getNumberTimeMinutes();

                List<Places> placesListAreaCopy = new ArrayList<Places>(placesListArea);
                int length;

                while (true) {
                    length = placesListAreaCopy.size();
                    if (length == 0) break;

                    Random random = new Random();
                    Places places = placesListAreaCopy.get(random.nextInt(length));

                    long timeGoTwoPlaces = 0;
                    if (placesBefore != null)
                        timeGoTwoPlaces = calculatorTimeGo(placesBefore, places, vehicle);

                    if (numberTimeMinutes >= (places.getMinTimePlaces() + timeGoTwoPlaces)
                            && (places.isFull() || (start.toLocalTime().isAfter(places.getBeginDay()) && start.toLocalTime().isBefore(places.getEndDay())))) {

                        long numberTimeMinutesForPlace = places.getMaxTimePlaces();
                        if (numberTimeMinutes < (places.getMaxTimePlaces() + timeGoTwoPlaces)) {
                            numberTimeMinutesForPlace = numberTimeMinutes - timeGoTwoPlaces;
                        }

                        if (s.isOptional()) {
                            planItems.add(PlanItem.builder()
                                    .imageUrl(!places.getImages().isEmpty() ? places.getImages().stream().findFirst().get().getUrlImage() : "https://www.google.com/url?sa=i&url=https%3A%2F%2F3gwifi.net%2Ftravel-la-gi%2F&psig=AOvVaw2oAnTpl2Cc_oltXbBJHLJb&ust=1699943380832000&source=images&cd=vfe&opi=89978449&ved=0CBEQjRxqFwoTCOCUlt2swIIDFQAAAAAdAAAAABAE")
                                    .startTime(start.plusMinutes(timeGoTwoPlaces))
                                    .title(places.getTitle())
                                    .content(places.getDescription())
                                    .money(places.getCost())
                                    .note("")
                                    .isOptional(s.isOptional())
                                    .index(s.getIndex())
                                    .placesId(places.getId())
                                    .plan(plan)
                                    .timeGoTwoPlaces(timeGoTwoPlaces)
                                    .build()
                            );

                            placesListArea.remove(places);
                            placesListAreaCopy.remove(places);

                            //khi chọn được địa điểm r thì trừ numberTimeMinutes -= (place.gettime +time go)
                            placesBefore = places;
                            start = start.plusMinutes(numberTimeMinutesForPlace + timeGoTwoPlaces);
                            numberTimeMinutes = numberTimeMinutes - (numberTimeMinutesForPlace + timeGoTwoPlaces);
                        } else {
                            if (cost.compareTo(places.getCost()) >= 0) {
                                planItems.add(PlanItem.builder()
                                        .imageUrl(!places.getImages().isEmpty() ? places.getImages().stream().findFirst().get().getUrlImage() : "https://www.google.com/url?sa=i&url=https%3A%2F%2F3gwifi.net%2Ftravel-la-gi%2F&psig=AOvVaw2oAnTpl2Cc_oltXbBJHLJb&ust=1699943380832000&source=images&cd=vfe&opi=89978449&ved=0CBEQjRxqFwoTCOCUlt2swIIDFQAAAAAdAAAAABAE")
                                        .startTime(start.plusMinutes(timeGoTwoPlaces))
                                        .title(places.getTitle())
                                        .content(places.getDescription())
                                        .money(places.getCost())
                                        .note("")
                                        .isOptional(s.isOptional())
                                        .index(s.getIndex())
                                        .placesId(places.getId())
                                        .plan(plan)
                                        .timeGoTwoPlaces(timeGoTwoPlaces)
                                        .build()
                                );

                                cost = cost.subtract(places.getCost()); //trừ cost
                                expenseOnePeople = expenseOnePeople.add(places.getCost()); //thêm tiền đã dùng
                                costPlay = costPlay.add(places.getCost());

                                placesListArea.remove(places);
                                placesListAreaCopy.remove(places);

                                //khi chọn được địa điểm r thì trừ numberTimeMinutes -= (place.gettime +time go)
                                placesBefore = places;
                                start = start.plusMinutes(numberTimeMinutesForPlace + timeGoTwoPlaces);
                                numberTimeMinutes = numberTimeMinutes - (numberTimeMinutesForPlace + timeGoTwoPlaces);
                            } else {
                                placesListAreaCopy.remove(places);
                            }
                        }
                    } else {
                        placesListAreaCopy.remove(places);
                    }
                }

            }
        }

        categories.add(categoryRepository.findByName(phanLoaiArray[phanLoai])
                .orElseThrow(() ->
                        new IllegalArgumentException("Category not found with name " + phanLoaiArray[phanLoai]))
        );

        //sắp xếp
        // sắp xếp từ be đến lon start time
        Collections.sort(planItems, Comparator.comparing(PlanItem::getStartTime));
        //tìm khoảng cách - va thoi gian giữa các plan item
        calculatorDistancePlanItems(planItems, vehicle);

        plan.setTitle("Chuyến du lịch " + planForm.getDestination());
        plan.setLocation(Address.builder()
                .latitude(planForm.getLocationLatitude())
                .longitude(planForm.getLocationLongitude())
                .build());
        plan.setDestination(planForm.getDestination());
        plan.setBeginDate(planForm.getBeginDate());
        plan.setEndDate(newEndDate);
        plan.setNumberPeople(planForm.getNumberPeople());
        plan.setDistance(distance);
        plan.setEstimatedTotalDistance(estimatedTotalDistance);
        plan.setNumberVehicle((int) numberVehicle);
        plan.setExpense(expenseOnePeople);
        plan.setCostVehicle(costVehicle);
        plan.setCostEat(costEat);
        plan.setCostPlay(costPlay);
        plan.setPlanItems(planItems);
        plan.setUser(userRepository.findByUsername(
                        SecurityUtils.getUsernameOfPrincipal().equals("anonymousUser") ? "admin" : SecurityUtils.getUsernameOfPrincipal())
                .orElseThrow(() ->
                        new UsernameNotFoundException("User not found with username " + SecurityUtils.getUsernameOfPrincipal())));
        plan.setCategories(categories);
        plan.setVehicle(vehicle);
        return PlanDto.toDto(planRepository.save(plan));
    }

    private void calculatorDistancePlanItems(List<PlanItem> planItems, Vehicle vehicle) {
        int length = planItems.size();

        for (int i = 0; i < length - 1; i++) {
            int finalI = i;
            Places places1 = placesRepository.findById(planItems.get(i).getPlacesId())
                    .orElseThrow(() ->
                            new NotFoundException("Places not found with id " + planItems.get(finalI).getPlacesId()));
            Places places2 = placesRepository.findById(planItems.get(i + 1).getPlacesId())
                    .orElseThrow(() ->
                            new NotFoundException("Places not found with id " + planItems.get(finalI + 1).getPlacesId()));
            double km = AppUtils.getGoTwoPlace(places1, places2);
            long time = (long) ((km / vehicle.getAverageSpeed()) * 1.3 * 60.0);

            planItems.get(finalI).setDistanceGoTwoPlaces(km);
            planItems.get(finalI).setTimeGoTwoPlaces(time);
        }
        planItems.get(length - 1).setDistanceGoTwoPlaces(0.0);
        planItems.get(length - 1).setTimeGoTwoPlaces(0);
    }


    private List<Sessions> countSessionsUpdate(LocalDateTime newBeginDate, long travelTimeMinutes) {
        List<Sessions> sessionsList = new ArrayList<>();
        long timeMinutesBegin = 0;
        Sessions sessions;
        int index = determineTime(newBeginDate);

        for (int i = index; i < 8; i++) {
            if (i == 0) {
                // index, start time, thời gian tối đa chơi tiếp, hạn tối đa bắt đầu chơi, thời gian lố qua session khác
                sessions = createSession(i, newBeginDate, timeMinutesBegin, travelTimeMinutes, 60, 60, 0);
            } else if (i == 1) {
                sessions = createSession(i, newBeginDate, timeMinutesBegin, travelTimeMinutes, 0, 15, 30);
            } else if (i == 2) {
                sessions = createSession(i, newBeginDate, timeMinutesBegin, travelTimeMinutes, 45, 20, 30);
            } else if (i == 3) {
                sessions = createSession(i, newBeginDate, timeMinutesBegin, travelTimeMinutes, 0, 20, 30);
            } else if (i == 4) {
                sessions = createSession(i, newBeginDate, timeMinutesBegin, travelTimeMinutes, 45, 20, 30);
            } else if (i == 5) {
                sessions = createSession(i, newBeginDate, timeMinutesBegin, travelTimeMinutes, 45, 20, 30);
            } else if (i == 6) {
                sessions = createSession(i, newBeginDate, timeMinutesBegin, travelTimeMinutes, 0, 20, 30);
            } else {
                sessions = createSession(i, newBeginDate, timeMinutesBegin, travelTimeMinutes, 45, 60, 1);
                sessions.setNumberTimeMinutes(sessions.getNumberTimeMinutes() + 2);
            }

            sessionsList.add(sessions);
            if (sessions.isEnd()) {
                break;
            }

            //gắn lại thời gian bắt đầu
            newBeginDate = newBeginDate.plusMinutes(sessions.getNumberTimeMinutes());
            timeMinutesBegin += sessions.getNumberTimeMinutes();
            //trừ thời gian còn lại
            travelTimeMinutes -= sessions.getNumberTimeMinutes();

            if (i == 7) i = -1;
        }

        return sessionsList;
    }

    private Sessions createSession(int index,
                                   LocalDateTime startTime,
                                   long timeMinutesBegin,
                                   long travelTimeMinutes,
                                   long maxTimeContinue,
                                   long maxStartTime,
                                   long expiredTime) {
        //thời gian từ lúc bắt đầu đến cuối giai đoạn
        long xToEndPhase = Duration.between(startTime.toLocalTime(), AppUtils.getTimeInit()[index + 1]).toMinutes();

        if (xToEndPhase >= travelTimeMinutes) { //hết thời gian du lịch
            if (travelTimeMinutes >= maxTimeContinue) { //nhưng nếu còn lớn hơn x phút thì vân đi chơi tiếp
                if (maxTimeContinue == 0) { //nếu x phút = 0 thì nới giờ lên thành 60 ph và là gợi ý
                    return Sessions.builder()
                            .timeMinutesBegin(timeMinutesBegin)
                            .numberTimeMinutes(60)
                            .index(index)
                            .isOptional(true)
                            .isEnd(true)
                            .isEnable(true)
                            .build();
                }
                return Sessions.builder()
                        .timeMinutesBegin(timeMinutesBegin)
                        .numberTimeMinutes(travelTimeMinutes)
                        .index(index)
                        .isOptional(false)
                        .isEnd(true)
                        .isEnable(true)
                        .build();
            } else { //nếu bé hơn thì gợi ý
                return Sessions.builder()
                        .timeMinutesBegin(timeMinutesBegin)
                        .numberTimeMinutes(60)
                        .index(index)
                        .isOptional(true)
                        .isEnd(true)
                        .isEnable(true)
                        .build();
            }
            //bắt đầu thời gian du lịch nhưng nếu bé hơn x phút thì vân đi chơi tiếp và chấp nhận lố
        } else if (xToEndPhase < maxStartTime && xToEndPhase < maxStartTime + expiredTime) {
            if (expiredTime == 0) { //nếu k cho lố thì k kích hoạt
                return Sessions.builder()
                        .timeMinutesBegin(timeMinutesBegin)
                        .numberTimeMinutes(xToEndPhase)
                        .index(index)
                        .isOptional(true)
                        .isEnd(false)
                        .isEnable(false)
                        .build();
            }
            return Sessions.builder()
                    .timeMinutesBegin(timeMinutesBegin)
                    .numberTimeMinutes(maxStartTime + expiredTime)
                    .index(index)
                    .isOptional(false)
                    .isEnd(false)
                    .isEnable(true)
                    .build();
        } else { //trường hợp còn lại
            return Sessions.builder()
                    .timeMinutesBegin(timeMinutesBegin)
                    .numberTimeMinutes(xToEndPhase)
                    .index(index)
                    .isOptional(false)
                    .isEnd(false)
                    .isEnable(true)
                    .build();
        }
    }

    //phân loại địa điểm theo cấp độ
    private List<Places> filterPlacesListGenerate(List<Places> places, int s) {
        String[] phanLoaiArray = new String[]{"Tiết kiệm", "Bình dân", "Vừa", "Cao", "Cao cấp"};
        List<Places> placesList = new ArrayList<>();

        for (int i = 0; i <= s; i++) {
            int finalI = i;
            Category category = categoryRepository.findByName(phanLoaiArray[i])
                    .orElseThrow(() ->
                            new IllegalArgumentException("Category not found with name " + phanLoaiArray[finalI]));
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
        return placesList;
    }

    //Lọc tất cả địa điểm 1 theo danh mục có danh mục nhất định
    private List<Places> filterByCategoryParent(List<Places> places, Category categoryParent) {
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

    private long calculatorTimeGo(Places places1, Places places2, Vehicle vehicle) {
        double km = AppUtils.getGoTwoPlace(places1, places2);
        return (long) ((km / vehicle.getAverageSpeed()) * 1.3 * 60.0); //sai so 1.3
    }
}