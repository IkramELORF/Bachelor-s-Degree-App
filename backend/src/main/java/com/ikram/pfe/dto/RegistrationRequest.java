package com.ikram.pfe.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.ikram.pfe.models.Address;
import java.time.LocalDate;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@JsonInclude(Include.NON_EMPTY)
public class RegistrationRequest {

  @NotEmpty(message = "Please type the first name")
  @NotNull(message = "Please type the last name")
  private String firstname;

  @NotEmpty(message = "Please type the last name")
  @NotNull(message = "Please type the last name")
  private String lastname;

  private LocalDate birthdate;

  @Valid
  private Address address;

  @NotEmpty(message = "Please type an email")
  @NotNull(message = "Please type an email")
  @Email(message = "Please type a correct email")
  private String email;

  @NotEmpty(message = "Please type a password")
  @NotNull(message = "Please type a password")
  @Size(min = 4, max = 16, message = "The password length should be between 4 and 16 characters")
  private String password;
}
