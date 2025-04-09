package com.ikram.pfe.dto;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SensorDataDto {

  private Integer id;
  private LocalDateTime date;
  private double rain;
  private double temperature;
  private double airHumidity;
  private double soilHumidity;
  private double windDirection;
  private double solarRadiation;
  private double windSpeed;

}
