package com.ikram.pfe.models;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.Data;

@Data
@Embeddable
public class Address {

  @NotEmpty(message = "Please type the street name")
  @NotNull(message = "Please type the street name")
  private String street;

  @NotEmpty(message = "Please type the house number")
  @NotNull(message = "Please type the house number")
  private String houseNumber;

  @NotEmpty(message = "Please type the postal code")
  @NotNull(message = "Please type the postal code")
  private String postalCode;

  @NotEmpty(message = "Please type the country")
  @NotNull(message = "Please type the country")
  private String country;

  private String additionalInformation;
}
