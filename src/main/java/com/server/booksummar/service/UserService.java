package com.server.booksummar.service;

import com.server.booksummar.domain.User;
import com.server.booksummar.dto.request.UserRequest;
import com.server.booksummar.dto.response.UserResponse;
import com.server.booksummar.mapper.UserMapper;
import com.server.booksummar.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private AuthorizationService authorizationService;

    public List<UserResponse> findAll() {
        List<User> users = userRepository.findAll();
        return users
                .stream()
                .map(userMapper::userToUserResponse)
                .collect(Collectors.toList());
    }

    public UserResponse findById(UUID idUser) {
        User user = userRepository.findById(idUser)
                .orElseThrow(() -> new NoSuchElementException("Nenhum usuário encontrado com o Id informado."));
        return userMapper.userToUserResponse(user);
    }

    public UserResponse update(UserRequest userRequest, UUID idUser) {
        User user = userRepository.findById(idUser)
                .orElseThrow(() -> new NoSuchElementException("Nenhum usuário encontrado com o Id informado"));

        userRequest.setPassword(new BCryptPasswordEncoder().encode(userRequest.getPassword()));

        userMapper.userUpdate(userRequest, user);
        user = userRepository.save(user);
        return userMapper.userToUserResponse(user);
    }

    public void delete(UUID idUser) {
        if (userRepository.existsById(idUser)) {
            userRepository.deleteById(idUser);
        } else {
            throw new NoSuchElementException("Nenhum usuário encontrado com o Id informado.");
        }
    }
}