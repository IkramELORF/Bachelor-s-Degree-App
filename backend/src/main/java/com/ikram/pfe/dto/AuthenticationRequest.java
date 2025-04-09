package com.ikram.pfe.dto;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Builder
@Data
public class AuthenticationRequest {

  @NotEmpty(message = "Please type an email")
  @NotNull(message = "Please type an email")
  @Email(message = "Please type a correct email")
  private String email;

  @NotEmpty(message = "Please type a password")
  @NotNull(message = "Please type a password")
  private String password;
}
