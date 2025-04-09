package com.ikram.pfe.models;

import javax.persistence.Embeddable;
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
@Embeddable
public class GeoLocation {

  @NotEmpty(message = "Please type the latitude name")
  @NotNull(message = "Please type the latitude name")
  private double latitude;

  @NotEmpty(message = "Please type the longitude name")
  @NotNull(message = "Please type the longitude name")
  private double longitude;
}
