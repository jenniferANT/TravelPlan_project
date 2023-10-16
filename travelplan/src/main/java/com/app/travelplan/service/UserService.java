package com.app.travelplan.service;

import com.app.travelplan.model.dto.UserDto;
import com.app.travelplan.model.entity.User;
import java.util.List;

public interface UserService {

    UserDto updateAvatar(Long id);

    UserDto getById(Long id);

    List<UserDto> getAll();

    UserDto getMyInfor();

    UserDto updateName(String name);
}
