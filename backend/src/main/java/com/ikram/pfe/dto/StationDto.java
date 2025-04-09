package com.ikram.pfe.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StationDto {

  private Integer id;
  private String name;
  private String registrationNumber;

}
