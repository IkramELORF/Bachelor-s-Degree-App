package com.ikram.pfe.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.ikram.pfe.models.GeoLocation;
import com.ikram.pfe.models.RequestStatus;
import java.time.LocalDateTime;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonInclude(Include.NON_EMPTY)
public class RequestDto {

  private Integer id;

  @NotEmpty(message = "Please type the title")
  @NotNull(message = "Please type the title")
  private String title;

  @NotEmpty(message = "Please type the description")
  @NotNull(message = "Please type the description")
  private String description;

  // the user info
  private Integer userId;
  @NotEmpty(message = "Please type the first name")
  @NotNull(message = "Please type the last name")
  private String userFirstname;

  @NotEmpty(message = "Please type the last name")
  @NotNull(message = "Please type the last name")
  private String userLastname;

  @NotEmpty(message = "Please type an email")
  @NotNull(message = "Please type an email")
  @Email(message = "Please type a correct email")
  private String userEmail;

  // the land info
  @NotEmpty(message = "Please type the land display name")
  @NotNull(message = "Please type the land display name")
  private String landDisplayName;
  private GeoLocation landLocation;

  private LocalDateTime requestDate;

  private RequestStatus status;

}
