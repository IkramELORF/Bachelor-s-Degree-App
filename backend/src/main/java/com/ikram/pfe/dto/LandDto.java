package com.ikram.pfe.dto;

import com.ikram.pfe.models.GeoLocation;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LandDto {

  private Integer id;

  @NotEmpty(message = "Please type the land name")
  @NotNull(message = "Please type the land name")
  private String displayName;

  private GeoLocation location;

}
