package com.jujuu.ourlog.service;

import com.jujuu.ourlog.dto.CreateUser;
import com.jujuu.ourlog.dto.UserDto;
import com.jujuu.ourlog.entity.User;
import com.jujuu.ourlog.repository.UserRepository;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    @Transactional
    public CreateUser.Response createUser(CreateUser.Request request) {

        User developer = User.builder()
                .name(request.getName())
                .build();

        userRepository.save(developer);
        return CreateUser.Response.fromEntity(developer);
    }

    public List<UserDto> getUsers() {
        return userRepository.findAll().stream()
                .map(UserDto::fromEntity)
                .collect(Collectors.toList());
    }
}
