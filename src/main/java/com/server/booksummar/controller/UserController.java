package com.server.booksummar.controller;

import com.server.booksummar.dto.request.UserRequest;
import com.server.booksummar.dto.response.UserResponse;
import com.server.booksummar.repository.UserRepository;
import com.server.booksummar.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService;

    @GetMapping
    @Operation(summary = "Busca todos os usuários")
    public ResponseEntity<List<UserResponse>> findAll() {
        return ResponseEntity.ok(userService.findAll());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Busca um usuário pelo Id")
    public ResponseEntity<UserResponse> findById(@PathVariable UUID id) {
        return ResponseEntity.ok(userService.findById(id));
    }

    @PutMapping("{id}")
    @Operation(summary = "Atualiza um usuário pelo Id")
    public ResponseEntity<UserResponse> update(@RequestBody @Valid UserRequest userRequest, @PathVariable UUID id) {
        return ResponseEntity.ok(userService.update(userRequest, id));
    }

    @DeleteMapping("{id}")
    @Operation(summary = "Deleta um usuário pelo Id")
    public void delete(@PathVariable UUID id) {
        userService.delete(id);
        ResponseEntity.ok();
    }
}