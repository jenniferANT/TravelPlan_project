package com.app.travelplan.service.impl;

import com.app.travelplan.model.entity.Cart;
import com.app.travelplan.model.entity.Plan;
import com.app.travelplan.model.entity.User;
import com.app.travelplan.repository.CartRepository;
import com.app.travelplan.repository.PlanRepository;
import com.app.travelplan.repository.UserRepository;
import com.app.travelplan.service.CartService;
import com.app.travelplan.utils.SecurityUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {
    private final PlanRepository planRepository;
    private final CartRepository cartRepository;
    private final UserRepository userRepository;

    @Override
    public String addFollow(long planId) {
        Plan plan = planRepository.findById(planId)
                .orElseThrow(() ->
                        new NotFoundException("Plan not found with id " + planId));
        String username = SecurityUtils.getUsernameOfPrincipal();

        if (!cartRepository.existsByUser_Username(username)) {
            User user = userRepository.findByUsername(username)
                    .orElseThrow(() ->
                            new NotFoundException("User not found with username " + username));

            Cart cart = Cart.builder()
                    .user(user)
                    .planList(new ArrayList<>())
                    .build();
            user.setCart(cart);
            cartRepository.save(cart);
        }

        Cart cart = cartRepository.findByUser_Username(SecurityUtils.getUsernameOfPrincipal())
                .orElseThrow(() ->
                        new NotFoundException("Cart not found with username " + SecurityUtils.getUsernameOfPrincipal()));

        if (cart.getPlanList().contains(plan)) {
            return "User followed this plan";
        }

        cart.getPlanList().add(plan);
        cartRepository.save(cart);
        return "Success";
    }

    @Override
    public String deleteFollow(long planId) {
        Plan plan = planRepository.findById(planId)
                .orElseThrow(() ->
                        new NotFoundException("Plan not found with id " + planId));
        Cart cart = cartRepository.findByUser_Username(SecurityUtils.getUsernameOfPrincipal())
                .orElseThrow(() ->
                        new NotFoundException("Cart not found with username " + SecurityUtils.getUsernameOfPrincipal()));

        if (cart.getPlanList().contains(plan)) {
            cart.getPlanList().remove(plan);
            cartRepository.save(cart);
            return "Success";
        }
        throw new IllegalArgumentException("Plan not exists my cart");
    }
}
