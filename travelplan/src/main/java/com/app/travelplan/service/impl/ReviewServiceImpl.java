package com.app.travelplan.service.impl;

import com.app.travelplan.model.dto.PlacesDto;
import com.app.travelplan.model.dto.ReviewDto;
import com.app.travelplan.model.entity.Image;
import com.app.travelplan.model.entity.Places;
import com.app.travelplan.model.entity.Review;
import com.app.travelplan.model.entity.User;
import com.app.travelplan.model.form.ReviewForm;
import com.app.travelplan.repository.ImageRepository;
import com.app.travelplan.repository.PlacesRepository;
import com.app.travelplan.repository.ReviewRepository;
import com.app.travelplan.repository.UserRepository;
import com.app.travelplan.service.ReviewService;
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
public class ReviewServiceImpl implements ReviewService {
    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;
    private final ImageRepository imageRepository;
    private final PlacesRepository placesRepository;

    @Override
    public ReviewDto save(ReviewForm reviewForm) {
        return ReviewDto.toDto(
                reviewRepository.save(
                        toEntity(reviewForm)
                )
        );
    }

    @Override
    public List<ReviewDto> getAll() {
        return reviewRepository.findAll()
                .stream()
                .map(ReviewDto::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<ReviewDto> getAllByPlaceId(long placeId) {
        return reviewRepository.findAllByPlaces_Id(placeId)
                .stream()
                .map(ReviewDto::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(long id) {
        Review review  = reviewRepository.findById(id)
                .orElseThrow(()->
                        new NotFoundException("Review not found with id " + id));

        if(review.getUser().getUsername().equals(SecurityUtils.getUsernameOfPrincipal())) {
            review.getPlaces().getReviews().remove(review);
            reviewRepository.delete(review);
            return;
        }
        throw new IllegalArgumentException("Delete places not permit");
    }

    @Override
    public ReviewDto update(ReviewForm reviewForm, long id) {
        Review review  = reviewRepository.findById(id)
                .orElseThrow(()->
                        new NotFoundException("Review not found with id " + id));

        if(review.getUser().getUsername().equals(SecurityUtils.getUsernameOfPrincipal())) {
            Review review1 = toEntity(reviewForm);
            review1.setId(id);
            return ReviewDto.toDto(reviewRepository.save(review1));
        }
        throw new IllegalArgumentException("Update places not permit");
    }

    @Override
    public ReviewDto getById(long id) {
        return ReviewDto.toDto(
                reviewRepository.findById(id)
                        .orElseThrow(()->
                                new NotFoundException("Review not found with id " + id))
        );
    }

    private Review toEntity(ReviewForm reviewForm) {
        List<Image> images = new ArrayList<Image>();

        List<Long> temp = reviewForm.getImageId();
        for (long i : temp) {
            images.add(
                    imageRepository.findById(i)
                            .orElseThrow(()->
                                    new NotFoundException("Image not fount with id "+i))
            );
        }
        User user = userRepository.findByUsername(SecurityUtils.getUsernameOfPrincipal())
                .orElseThrow(()->
                        new UsernameNotFoundException("Username not found with " + SecurityUtils.getUsernameOfPrincipal()));
        Places places = placesRepository.findById(reviewForm.getPlaceId())
                .orElseThrow(()->
                        new NotFoundException("Place not found with " + reviewForm.getPlaceId()));

        Review review = Review.builder()
                .point(reviewForm.getPoint())
                .content(reviewForm.getContent())
                .imageList(images)
                .user(user)
                .places(places)
                .build();

        review.getUser().getReviews().add(review);
        review.getPlaces().getReviews().add(review);

        return  review;
    }
}
