package com.app.travelplan.service.impl;

import com.app.travelplan.model.dto.PlacesDto;
import com.app.travelplan.model.dto.ListResponse;
import com.app.travelplan.model.entity.*;
import com.app.travelplan.model.form.PlacesForm;
import com.app.travelplan.repository.*;
import com.app.travelplan.service.GeneralService;
import com.app.travelplan.service.PlacesService;
import com.app.travelplan.utils.SecurityUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PlacesServiceIml implements PlacesService {
    private final ImageRepository imageRepository;
    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;
    private final PlacesRepository placesRepository;
    private final AddressRepository addressRepository;
    private final LinkRepository linkRepository;
    private final FollowRepository followRepository;

    private final GeneralService generalService;

    @Override
    public PlacesDto save(PlacesForm placesForm) {
        Places places = toEntity(placesForm);

        places.getUser().getPlaces().add(places);
        return PlacesDto.toDto(placesRepository.save(places));
    }

    @Override
    public ListResponse getAll(int pageNo, int pageSize, String sortBy, String sortDir) {
        Sort sort = sortDir.equals(Sort.Direction.ASC.name())
                ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);

        Page<Places> listPage = placesRepository.findAll(pageable);
        List<Places> listPlaces = listPage.getContent();
        List<PlacesDto> content = listPlaces.stream().map(PlacesDto::toDto).toList();

        ListResponse<PlacesDto> listResponse = new ListResponse<>();

        return listResponse.toListResponse(
                listResponse,
                content,
                listPage.getNumber(),
                listPage.getSize(),
                listPage.getTotalElements(),
                listPage.getTotalPages(),
                listPage.isLast()
        );
    }

    @Override
    public ListResponse getMyPlace(int pageNo, int pageSize, String sortBy, String sortDir) {
        Sort sort = sortDir.equals(Sort.Direction.ASC.name())
                ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);

        Page<Places> listPage = placesRepository.findAllByUser_Username(pageable, SecurityUtils.getUsernameOfPrincipal());
        List<Places> listPlaces = listPage.getContent();
        List<PlacesDto> content = listPlaces.stream().map(PlacesDto::toDto).toList();

        ListResponse<PlacesDto> listResponse = new ListResponse<>();

        return listResponse.toListResponse(
                listResponse,
                content,
                listPage.getNumber(),
                listPage.getSize(),
                listPage.getTotalElements(),
                listPage.getTotalPages(),
                listPage.isLast()
        );
    }

    @Override
    public void deleteById(long id) {
        Places places = placesRepository.findById(id)
                .orElseThrow(() ->
                        new NotFoundException("Places not found with id " + id));
        if (!SecurityUtils.getRoleOfPrincipal().equals("ROLE_ADMIN") ||
                !places.getUser().getUsername().equals(SecurityUtils.getUsernameOfPrincipal())) {
            places.getUser().getPlaces().remove(places);
            placesRepository.delete(places);
            return;
        }
        throw new IllegalArgumentException("Not permit");

    }

    @Override
    public PlacesDto update(PlacesForm placesForm, long id) {
        Places places = placesRepository.findById(id)
                .orElseThrow(() ->
                        new NotFoundException("Places not found with id " + id));
        if (SecurityUtils.getRoleOfPrincipal().equals("ROLE_ADMIN") ||
                places.getUser().getUsername().equals(SecurityUtils.getUsernameOfPrincipal())) {
            Places p = toEntity(placesForm);
            p.setId(id);
            return PlacesDto.toDto(placesRepository.save(p));
        }
        throw new IllegalArgumentException("Not permit");

    }

    @Override
    public PlacesDto getById(long id) {
        return PlacesDto.toDto(placesRepository.findById(id)
                .orElseThrow(() ->
                        new NotFoundException("Places not found with id " + id)));
    }

    @Override
    public List<PlacesDto> getMyFollow() {
        Follow follow = followRepository.findByUser_Username(SecurityUtils.getUsernameOfPrincipal())
                .orElseThrow(() ->
                        new NotFoundException("Follow not found with username " + SecurityUtils.getUsernameOfPrincipal()));
        return follow.getPlaces()
                .stream()
                .map(PlacesDto::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public PlacesDto addCategoryToPlace(long placeId, long[] ids) {
        Places places = placesRepository.findById(placeId)
                .orElseThrow(() ->
                        new NotFoundException("Places not found with id " + placeId));

        if (SecurityUtils.getRoleOfPrincipal().equals("ROLE_ADMIN") ||
                places.getUser().getUsername().equals(SecurityUtils.getUsernameOfPrincipal())) {
            for (long id :
                    ids) {
                if (!this.arrayCategoryContains(id, places.getCategories())) {
                    Category category = categoryRepository.findById(id)
                            .orElseThrow(() ->
                                    new NotFoundException("Category not found with id " + id));

                    places.getCategories().add(category);
                }
            }
            return PlacesDto.toDto(
                    placesRepository.save(places));

        }
        throw new IllegalArgumentException("Not permit");
    }

    @Override
    public PlacesDto delCategoryToPlace(long placeId, long[] ids) {
        Places places = placesRepository.findById(placeId)
                .orElseThrow(() ->
                        new NotFoundException("Places not found with id " + placeId));

        if (SecurityUtils.getRoleOfPrincipal().equals("ROLE_ADMIN") ||
                places.getUser().getUsername().equals(SecurityUtils.getUsernameOfPrincipal())) {
            for (long id :
                    ids) {
                if (this.arrayCategoryContains(id, places.getCategories())) {
                    Category category = categoryRepository.findById(id)
                            .orElseThrow(() ->
                                    new NotFoundException("Category not found with id " + id));

                    places.getCategories().remove(category);
                }
            }
            return PlacesDto.toDto(
                    placesRepository.save(places));
        }
        throw new IllegalArgumentException("Not permit");
    }

    @Override
    public PlacesDto addImageToPlace(long placeId, long[] ids) {
        Places places = placesRepository.findById(placeId)
                .orElseThrow(() ->
                        new NotFoundException("Places not found with id " + placeId));

        if (SecurityUtils.getRoleOfPrincipal().equals("ROLE_ADMIN") ||
                places.getUser().getUsername().equals(SecurityUtils.getUsernameOfPrincipal())) {
            for (long id :
                    ids) {
                if (!this.arrayImageContains(id, places.getImages())) {
                    Image image = imageRepository.findById(id)
                            .orElseThrow(() ->
                                    new NotFoundException("Image not found with id " + id));

                    places.getImages().add(image);
                }
            }
            return PlacesDto.toDto(
                    placesRepository.save(places));

        }
        throw new IllegalArgumentException("Not permit");
    }

    @Override
    public PlacesDto delImageToPlace(long placeId, long[] ids) {
        Places places = placesRepository.findById(placeId)
                .orElseThrow(() ->
                        new NotFoundException("Places not found with id " + placeId));

        if (SecurityUtils.getRoleOfPrincipal().equals("ROLE_ADMIN") ||
                places.getUser().getUsername().equals(SecurityUtils.getUsernameOfPrincipal())) {
            for (long id :
                    ids) {
                if (this.arrayImageContains(id, places.getImages())) {
                    Image image = imageRepository.findById(id)
                            .orElseThrow(() ->
                                    new NotFoundException("Image not found with id " + id));

                    places.getImages().remove(image);
                }
            }
            return PlacesDto.toDto(
                    placesRepository.save(places));
        }
        throw new IllegalArgumentException("Not permit");
    }

    private boolean arrayCategoryContains(long id, List<Category> categories) {
        for (Category c :
                categories) {
            if (id == c.getId()) {
                return true;
            }
        }
        return false;
    }

    private boolean arrayImageContains(long id, List<Image> images) {
        for (Image c :
                images) {
            if (id == c.getId()) {
                return true;
            }
        }
        return false;
    }

    private Places toEntity(PlacesForm placesForm) {
        if (placesForm.getBeginDay().isAfter(placesForm.getEndDay())) {
            throw new IllegalArgumentException("Begin day must be before end day");
        }
        List<Image> images = generalService.getAllImageByArrayId(placesForm.getImageId());
        List<Category> categories = generalService.getAllCategoryByArrayId(placesForm.getCategoryId());
        //du lịch lớn hơn 4tr/ng:
        String s = "";
        if (placesForm.getCost().compareTo(BigDecimal.valueOf(500000)) >= 0) {
            s = "Cao cấp";
        } else if (placesForm.getCost().compareTo(BigDecimal.valueOf(150000)) >= 0) {
            s = "Cao";
        } else if (placesForm.getCost().compareTo(BigDecimal.valueOf(50000)) >= 0) {
            s = "Vừa";
        } else if (placesForm.getCost().compareTo(BigDecimal.valueOf(30000)) >= 0) {
            s = "Bình dân";
        } else {
            s = "Tiết kiệm";
        }
        categories.add(categoryRepository.findByName(s).get());

        User user = userRepository.findByUsername(SecurityUtils.getUsernameOfPrincipal())
                .orElseThrow(() ->
                        new UsernameNotFoundException("Username not found with " + SecurityUtils.getUsernameOfPrincipal()));

        Address address = addressRepository.findById(placesForm.getAddressId())
                .orElseThrow(() ->
                        new NotFoundException("Category not fount with id " + placesForm.getAddressId()));

        Link link = linkRepository.findById(placesForm.getLinkId())
                .orElseThrow(() ->
                        new NotFoundException("Category not fount with id " + placesForm.getLinkId()));

        Places places = Places.builder()
                .title(placesForm.getTitle())
                .phoneNumber(placesForm.getPhoneNumber())
                .cost(placesForm.getCost())
                .point(0)
                .description(placesForm.getDescription())
                .minTimePlaces(placesForm.getMinTimePlaces())
                .user(user)
                .categories(categories)
                .images(images)
                .link(link)
                .maxTimePlaces(placesForm.getMaxTimePlaces())
                .address(address)
                .isFull(placesForm.isFull())
                .beginDay(placesForm.getBeginDay())
                .endDay(placesForm.getEndDay())
                .build();
        places.getAddress().setPlaces(places);
        places.getLink().setPlaces(places);

        return places;
    }

}
