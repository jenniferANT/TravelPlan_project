package com.app.travelplan.service.impl;

import com.app.travelplan.model.dto.UserDto;
import com.app.travelplan.model.entity.Image;
import com.app.travelplan.model.entity.User;
import com.app.travelplan.repository.ImageRepository;
import com.app.travelplan.repository.UserRepository;
import com.app.travelplan.service.UserService;
import com.app.travelplan.utils.SecurityUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ImageRepository imageRepository;

    @Override
    public UserDto updateAvatar(Long id) {
        User user = userRepository.findByUsername(SecurityUtils.getUsernameOfPrincipal())
                .orElseThrow(() ->
                        new NotFoundException("User not found with id " + SecurityUtils.getUsernameOfPrincipal()));

        Image image = imageRepository.findById(id)
                .orElseThrow(() ->
                        new NotFoundException("Image not found with id " + id));

        image.setUser(user);
        user.setAvatar(image);
        return UserDto.toDto(userRepository.save(user));
    }

    @Override
    public UserDto updateName(String name) {
        User user = userRepository.findByUsername(SecurityUtils.getUsernameOfPrincipal())
                .orElseThrow(() ->
                        new NotFoundException("User not found with id " + SecurityUtils.getUsernameOfPrincipal()));

        user.setName(name);
        return UserDto.toDto(userRepository.save(user));
    }

    @Override
    public UserDto getById(Long id) {
        return UserDto.toDto(userRepository.findById(id)
                .orElseThrow(() ->
                        new NotFoundException("User not found with id " + id)));
    }

    @Override
    public List<UserDto> getAll() {
        return userRepository.findAll()
                .stream()
                .map(UserDto::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public UserDto getMyInfor() {
        return UserDto.toDto(userRepository.findByUsername(SecurityUtils.getUsernameOfPrincipal())
                .orElseThrow(() ->
                        new NotFoundException("User not found with id " + SecurityUtils.getUsernameOfPrincipal())));
    }
}
