package com.app.travelplan.service.impl;

import com.app.travelplan.model.entity.Follow;
import com.app.travelplan.model.entity.Places;
import com.app.travelplan.model.entity.User;
import com.app.travelplan.repository.FollowRepository;
import com.app.travelplan.repository.PlacesRepository;
import com.app.travelplan.repository.UserRepository;
import com.app.travelplan.service.FollowService;
import com.app.travelplan.utils.SecurityUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class FollowServiceImpl implements FollowService {
    private final PlacesRepository placesRepository;
    private final FollowRepository followRepository;
    private final UserRepository userRepository;

    @Override
    public String addFollow(long placeId) {
        Places places = placesRepository.findById(placeId)
                .orElseThrow(() ->
                        new NotFoundException("Places not found with id " + placeId));
        String username = SecurityUtils.getUsernameOfPrincipal();

        if (!followRepository.existsByUser_Username(username)) {
            User user = userRepository.findByUsername(username)
                    .orElseThrow(() ->
                            new NotFoundException("User not found with username " + username));

            Follow follow = Follow.builder()
                    .quantity(0)
                    .user(user)
                    .places(new ArrayList<>())
                    .build();
            user.setFollow(follow);
            followRepository.save(follow);
        }

        Follow follow = followRepository.findByUser_Username(SecurityUtils.getUsernameOfPrincipal())
                .orElseThrow(() ->
                        new NotFoundException("Follow not found with username " + SecurityUtils.getUsernameOfPrincipal()));

        if (follow.getPlaces().contains(places)) {
            return "User followed this place";
        }

        follow.getPlaces().add(places);
        follow.setQuantity(follow.getQuantity() + 1);
        followRepository.save(follow);
        return "Success";
    }

    @Override
    public String deleteFollow(long placeId) {
        Places places = placesRepository.findById(placeId)
                .orElseThrow(() ->
                        new NotFoundException("Places not found with id " + placeId));
        Follow follow = followRepository.findByUser_Username(SecurityUtils.getUsernameOfPrincipal())
                .orElseThrow(() ->
                        new NotFoundException("Follow not found with username " + SecurityUtils.getUsernameOfPrincipal()));

        if (follow.getPlaces().contains(places)) {
            follow.getPlaces().remove(places);
            follow.setQuantity(follow.getQuantity() - 1);
            followRepository.save(follow);
            return "Success";
        }
        throw new IllegalArgumentException("Places not exists my follow");
    }
}
