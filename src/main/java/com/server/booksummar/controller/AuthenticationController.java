package com.server.booksummar.controller;

import com.server.booksummar.configuration.security.TokenService;
import com.server.booksummar.domain.EmailDetails;
import com.server.booksummar.domain.User;
import com.server.booksummar.dto.request.AuthenticationRequest;
import com.server.booksummar.dto.request.PasswordRecoveryRequest;
import com.server.booksummar.dto.request.RegisterRequest;
import com.server.booksummar.dto.response.LoginResponse;
import com.server.booksummar.dto.response.RegisterResponse;
import com.server.booksummar.mapper.RegisterMapper;
import com.server.booksummar.repository.UserRepository;
import com.server.booksummar.service.AuthorizationService;
import com.server.booksummar.service.EmailService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.*;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository repository;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private RegisterMapper registerMapper;

    @Autowired
    private AuthorizationService authorizationService;

    @Autowired
    private EmailService emailService;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/login")
    @Operation(summary = "Realiza o login de um usuário")
    public ResponseEntity<?> login(@RequestBody @Valid AuthenticationRequest authenticationRequest) {
        try {
            var usernamePassword = new UsernamePasswordAuthenticationToken(authenticationRequest.getLogin(), authenticationRequest.getPassword());
            var auth = authenticationManager.authenticate(usernamePassword);
            var token = tokenService.generateToken((User) auth.getPrincipal());
            LoginResponse loginResponse = new LoginResponse(token, ((User) auth.getPrincipal()).getId());

            return ResponseEntity.ok(loginResponse);
        } catch (DisabledException e) {
            throw new DisabledException("Sua conta está desativada. Entre em contato com o suporte.");
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException("Credenciais inválidas. Verifique seu login e senha.");
        } catch (LockedException e) {
            throw new LockedException("Sua conta está bloqueada. Entre em contato com o suporte.");
        } catch (AuthenticationException e) {
            throw new RuntimeException("Falha na autenticação. Verifique suas credenciais.");
        } catch (Exception e) {
            throw new RuntimeException("Falha no login.");
        }
    }

    @PostMapping("/register")
    @Operation(summary = "Registra um novo usuário")
    public ResponseEntity<?> register(@RequestBody @Valid RegisterRequest registerRequest) {
        try {
            if (repository.findByLogin(registerRequest.getLogin()) != null) {
                throw new DataIntegrityViolationException("Já existe um usuário com o login: " + registerRequest.getLogin());
            }

            String encryptedPassword = new BCryptPasswordEncoder().encode(registerRequest.getPassword());
            User newUser = new User(registerRequest.getName(), registerRequest.getLogin(), registerRequest.getAvatarURL(), encryptedPassword, registerRequest.getRole());

            User savedUser = repository.save(newUser);

            RegisterResponse registerResponse = registerMapper.UserToRegisterResponse(savedUser);

            EmailDetails emailDetails = new EmailDetails();
            emailDetails.setSubject("Feedback Books - REGISTRO");
            emailDetails.setMessageBody("Registro realizado com sucesso! " + savedUser.getName() + " seja bem vindo(a) ao Book Feedback, a sua plataforma de resumos de livros.");
            emailDetails.setRecipient(savedUser.getUsername());
            emailService.sendEmail(emailDetails);

            return ResponseEntity.ok(registerResponse);
        } catch (DataIntegrityViolationException ex) {
            throw new DataIntegrityViolationException(ex.getMessage());
        } catch (Exception ex) {
            throw new RuntimeException(ex.getMessage());
        }
    }

    @PostMapping("/requestNewPassword")
    @Operation(summary = "Geração de nova senha para um usuário")
    public ResponseEntity<String> requestNewPassword(@RequestBody @Valid PasswordRecoveryRequest passwordRecoveryRequest) {
        try {
            User user = (User) authorizationService.loadUserByUsername(passwordRecoveryRequest.getLogin());

            String randomCode = UUID.randomUUID().toString().replace("-", "").substring(0, 8);
            String encryptedPassword = new BCryptPasswordEncoder().encode(randomCode);
            user.setPassword(encryptedPassword);
            userRepository.save(user);

            EmailDetails emailDetails = new EmailDetails();
            emailDetails.setSubject("BOOK FEEDBACK - RECUPERAÇÃO DE SENHA");
            emailDetails.setMessageBody("Sua nova senha é: " + randomCode + " Use esta senha em seu próximo login e cadastre uma nova em Alteração de Dados.");
            emailDetails.setRecipient(user.getUsername());
            emailService.sendEmail(emailDetails);

            return ResponseEntity.ok("Nova senha encaminhada com sucesso");

        } catch (NullPointerException ex) {
            throw new RuntimeException("Nenhum usuário encontrado com este login");
        }
    }
}
