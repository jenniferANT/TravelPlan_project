package com.app.travelplan.service.impl;

import com.app.travelplan.model.dto.ShareDto;
import com.app.travelplan.model.entity.Places;
import com.app.travelplan.model.entity.Plan;
import com.app.travelplan.model.entity.Share;
import com.app.travelplan.model.entity.User;
import com.app.travelplan.model.form.ShareForm;
import com.app.travelplan.repository.PlacesRepository;
import com.app.travelplan.repository.PlanRepository;
import com.app.travelplan.repository.ShareRepository;
import com.app.travelplan.repository.UserRepository;
import com.app.travelplan.service.ShareService;
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
public class ShareServiceImpl implements ShareService {
    private final PlanRepository planRepository;
    private final UserRepository userRepository;
    private final ShareRepository shareRepository;

    @Override
    public String share(ShareForm shareForm) {
        Plan plan = planRepository.findById(shareForm.getPlanId())
                .orElseThrow(() ->
                        new NotFoundException("Plan not found with id " + shareForm.getPlanId()));
        String[] receiverUsername = shareForm.getReceiverUsername();

        if (receiverUsername == null) throw new IllegalArgumentException("Receiver Username null");

        List<User> receiver = new ArrayList<>();
        for (String r :
                receiverUsername) {
            receiver.add(
                    userRepository.findByUsername(r)
                            .orElseThrow(() ->
                                    new UsernameNotFoundException("User not found with username " + r))
            );
        }

        shareRepository.save(Share.builder()
                .message(shareForm.getMessage())
                .remitter(userRepository.findByUsername(SecurityUtils.getUsernameOfPrincipal())
                        .orElseThrow(() ->
                                new UsernameNotFoundException("User not found with username " + SecurityUtils.getUsernameOfPrincipal())))
                .receiver(receiver)
                .plan(plan)
                .build()
        );
        return "Share success";
    }

    @Override
    public String delReceiver(long shareId, String receiver) {
        Share share = shareRepository.findById(shareId)
                .orElseThrow(() ->
                        new UsernameNotFoundException("Share not found with username " + shareId));
        User user = userRepository.findByUsername(receiver)
                .orElseThrow(() ->
                        new UsernameNotFoundException("User not found with username " + receiver));
        share.getReceiver().remove(user);
        return "Success";
    }

    @Override
    public String delShare(long shareId) {
        shareRepository.deleteById(shareId);
        return "Success";
    }

    @Override
    public List<ShareDto> getMyShareForOther() {
        return shareRepository.findAllByRemitter_Username(SecurityUtils.getUsernameOfPrincipal())
                .stream()
                .map(ShareDto::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<ShareDto> getOtherShareForMe() {
        return shareRepository.findAllByReceiverContains(userRepository.findByUsername(SecurityUtils.getUsernameOfPrincipal())
                        .orElseThrow(() ->
                                new UsernameNotFoundException("User not found with username " + SecurityUtils.getUsernameOfPrincipal())))
                .stream()
                .map(ShareDto::toDto)
                .collect(Collectors.toList());
    }
}
