package com.ikram.pfe.controllers;

import com.ikram.pfe.dto.AuthenticationRequest;
import com.ikram.pfe.dto.AuthenticationResponse;
import com.ikram.pfe.dto.RegistrationRequest;
import com.ikram.pfe.dto.UserDto;
import com.ikram.pfe.services.AuthenticationService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/authentication")
@RequiredArgsConstructor
public class AuthenticationController {

  private final AuthenticationService service;

  @PostMapping("/register")
  public ResponseEntity<AuthenticationResponse> register(
      @RequestBody RegistrationRequest request
  ) {
    return ResponseEntity.ok(service.register(request));
  }

  @PostMapping("/login")
  public ResponseEntity<AuthenticationResponse> login(
      @RequestBody AuthenticationRequest request
  ) {
    return ResponseEntity.ok(service.login(request));
  }

  @PutMapping("/edit-profile")
  public ResponseEntity<UserDto> updateProfile(
      @RequestBody UserDto userDto
  ) {
    return ResponseEntity.ok(service.updateProfile(userDto));
  }

  @PostMapping("/create-user")
  @PreAuthorize("hasRole('ADMIN')")
  public ResponseEntity<String> createUser(
      @RequestBody UserDto userDto
  ) {
    service.createUser(userDto);
    return ResponseEntity.ok("User created");
  }

  @GetMapping("/all-users")
  @PreAuthorize("hasRole('ADMIN')")
  public ResponseEntity<List<UserDto>> findAllUsers() {
    return ResponseEntity.ok(service.findAllUsers());
  }
}
