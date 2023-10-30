package com.app.travelplan.service.impl;

import com.app.travelplan.model.dto.PlacesDto;
import com.app.travelplan.model.entity.*;
import com.app.travelplan.model.form.PlacesForm;
import com.app.travelplan.repository.*;
import com.app.travelplan.service.PlacesService;
import com.app.travelplan.utils.SecurityUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.ArrayList;
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

    @Override
    public PlacesDto save(PlacesForm placesForm) {
        Places places = toEntity(placesForm);
        places.getUser().getPlaces().add(places);
        return PlacesDto.toDto(placesRepository.save(places));
    }

    @Override
    public List<PlacesDto> getAll() {
        return placesRepository.findAll()
                .stream()
                .map(PlacesDto::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(long id) {
        Places places = placesRepository.findById(id)
                .orElseThrow(()->
                        new NotFoundException("Places not found with id " + id));
        places.getUser().getPlaces().remove(places);
        placesRepository.delete(places);
    }

    @Override
    public PlacesDto update(PlacesForm placesForm, long id) {
        if(placesRepository.existsById(id)) {
            Places places = toEntity(placesForm);
            places.setId(id);
            return PlacesDto.toDto(placesRepository.save(places));
        }
        throw new NotFoundException("Places not found with id " + id);
    }

    @Override
    public PlacesDto getById(long id) {
        return PlacesDto.toDto(placesRepository.findById(id)
                .orElseThrow(()->
                        new NotFoundException("Places not found with id " + id)));
    }

    private Places toEntity(PlacesForm placesForm) {
        List<Image> images = new ArrayList<Image>();

        List<Long> temp = placesForm.getImageId();
        for (long i : temp) {
            images.add(
                    imageRepository.findById(i)
                            .orElseThrow(()->
                                    new NotFoundException("Image not fount with id "+i))
            );
        }

        List<Category> categories = new ArrayList<Category>();
        temp = placesForm.getCategoryId();
        for (long i : temp) {
            categories.add(
                    categoryRepository.findById(i)
                            .orElseThrow(()->
                                    new NotFoundException("Category not fount with id "+i))
            );
        }

        User user = userRepository.findByUsername(SecurityUtils.getUsernameOfPrincipal())
                .orElseThrow(()->
                        new UsernameNotFoundException("Username not found with " + SecurityUtils.getUsernameOfPrincipal()));

        Address address = addressRepository.findById(placesForm.getAddressId())
                .orElseThrow(()->
                        new NotFoundException("Category not fount with id "+placesForm.getAddressId()));

        Link link = linkRepository.findById(placesForm.getLinkId())
                .orElseThrow(()->
                        new NotFoundException("Category not fount with id "+placesForm.getLinkId()));

        Places places = Places.builder()
                .title(placesForm.getTitle())
                .phoneNumber(placesForm.getPhoneNumber())
                .cost(placesForm.getCost())
                .point(0)
                .user(user)
                .categories(categories)
                .images(images)
                .link(link)
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
