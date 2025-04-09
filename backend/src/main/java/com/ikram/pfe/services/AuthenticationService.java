package com.ikram.pfe.services;

import com.ikram.pfe.dto.AuthenticationRequest;
import com.ikram.pfe.dto.AuthenticationResponse;
import com.ikram.pfe.dto.RegistrationRequest;
import com.ikram.pfe.dto.UserDto;
import com.ikram.pfe.mappers.ObjectsMapper;
import com.ikram.pfe.models.AppUser;
import com.ikram.pfe.models.Role;
import com.ikram.pfe.models.RoleNames;
import com.ikram.pfe.repositories.RoleRepository;
import com.ikram.pfe.repositories.UserRepository;
import com.ikram.pfe.utils.JwtUtils;
import com.ikram.pfe.validators.ClassValidator;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

  private final UserRepository repository;
  private final RoleRepository roleRepository;
  private final PasswordEncoder passwordEncoder;
  private final JwtUtils jwtUtils;
  private final AuthenticationManager authenticationManager;
  private final ObjectsMapper mapper;
  private final ClassValidator validator;

  public AuthenticationResponse register(RegistrationRequest request) {
    validator.validate(request);
    repository.findByEmail(request.getEmail()).ifPresent(appUser -> {
      throw new IllegalStateException("Another user with the same email address already exists");
    });
    AppUser user = AppUser.builder()
        .firstname(request.getFirstname())
        .lastname(request.getLastname())
        .birthdate(request.getBirthdate())
        .address(request.getAddress())
        .email(request.getEmail())
        .password(
            passwordEncoder.encode(request.getPassword())
        )
        // check if we want to send a confirmation email to enable the user
        .enabled(true)
        .build();
    Optional<Role> optionalRole = roleRepository.findByRole(RoleNames.ROLE_USER);
    Role role;
    if (optionalRole.isEmpty()) {
      role = roleRepository.save(new Role(RoleNames.ROLE_USER));
    } else {
      role = optionalRole.get();
    }
    user.setRoles(Collections.singletonList(role));
    AppUser savedUser = repository.save(user);
    return new AuthenticationResponse(jwtUtils.generateToken(savedUser), mapper.toUserDto(savedUser));
  }

  public AuthenticationResponse login(AuthenticationRequest request) {
    validator.validate(request);
    authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(
            request.getEmail(),
            request.getPassword()
        )
    );
    AppUser user = repository.findByEmail(request.getEmail())
        .orElseThrow(() -> new UsernameNotFoundException("No user found"));
    return new AuthenticationResponse(jwtUtils.generateToken(user), mapper.toUserDto(user));
  }

  @Transactional
  public UserDto updateProfile(UserDto userDto) {
    validator.validate(userDto);
    AppUser user = repository.findById(userDto.getId())
        .orElseThrow(() -> new UsernameNotFoundException("No user was found to edit"));
    AppUser userToSave = mergeUserData(userDto);
    repository.save(userToSave);
    return userDto;
  }

  private AppUser mergeUserData(UserDto newUser) {
    AppUser userToUpdate = new AppUser();
    userToUpdate.setId(newUser.getId());
    userToUpdate.setAddress(newUser.getAddress());
    userToUpdate.setEmail(newUser.getEmail());
    userToUpdate.setFirstname(newUser.getFirstname());
    userToUpdate.setLastname(newUser.getLastname());
    if (StringUtils.hasLength(newUser.getPassword())) {
      userToUpdate.setPassword(passwordEncoder.encode(newUser.getPassword()));
    }
    Optional<Role> optionalRole = roleRepository.findByRole(newUser.getRoles().get(0).getRole());
    Role role;
    if (optionalRole.isEmpty()) {
      role = roleRepository.save(new Role(RoleNames.ROLE_USER));
    } else {
      role = optionalRole.get();
    }
    userToUpdate.setRoles(Collections.singletonList(role));
    return  userToUpdate;
  }

  public void createUser(UserDto userDto) {
    repository.findByEmail(userDto.getEmail()).ifPresent(appUser -> {
      throw new IllegalStateException("Email already in user");
    });
    AppUser user = AppUser.builder()
        .firstname(userDto.getFirstname())
        .lastname(userDto.getLastname())
        .email(userDto.getEmail())
        .address(userDto.getAddress())
        .roles(userDto.getRoles())
        .enabled(true)
        .password(passwordEncoder.encode(userDto.getPassword()))
        .build();
    Optional<Role> optionalRole = roleRepository.findByRole(userDto.getRoles().get(0).getRole());
    Role role;
    if (optionalRole.isEmpty()) {
      role = roleRepository.save(new Role(RoleNames.ROLE_USER));
    } else {
      role = optionalRole.get();
    }
    user.setRoles(Collections.singletonList(role));
    repository.save(user);
  }

  public List<UserDto> findAllUsers() {
    return repository.findAll().stream()
        .map(mapper::toUserDto)
        .collect(Collectors.toList());
  }
}
