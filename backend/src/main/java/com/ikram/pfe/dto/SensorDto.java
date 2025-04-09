package com.ikram.pfe.dto;

import com.ikram.pfe.models.SensorType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SensorDto {

  private Integer id;
  private String sensorName;
  private SensorType type;

}
