package com.ikram.pfe.models;

import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity
public class SensorData {

  @Id
  @GeneratedValue
  private Integer id;
  private LocalDateTime date;
  private double rain;
  private double temperature;
  private double airHumidity;
  private double soilHumidity;
  private double windDirection;
  private double solarRadiation;
  private double windSpeed;
  @ManyToOne
  @JoinColumn(name = "sensor_id")
  private Sensor sensor;
}
