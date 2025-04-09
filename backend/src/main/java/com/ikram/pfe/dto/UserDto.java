package com.ikram.pfe.dto;

import com.ikram.pfe.models.Address;
import com.ikram.pfe.models.Role;
import java.time.LocalDate;
import java.util.List;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

  private Integer id;

  @NotEmpty(message = "Please type the first name")
  @NotNull(message = "Please type the last name")
  private String firstname;

  @NotEmpty(message = "Please type the last name")
  @NotNull(message = "Please type the last name")
  private String lastname;

  private String email;

  private String password;

  private LocalDate birthdate;

  private Address address;

  private List<Role> roles;

}
