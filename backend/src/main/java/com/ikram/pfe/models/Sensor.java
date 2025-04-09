package com.ikram.pfe.models;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import lombok.Data;

@Data
@Entity
public class Sensor {

  @Id
  @GeneratedValue
  private Integer id;
  @Column(unique = true)
  private String sensorName;
  @Enumerated(EnumType.STRING)
  private SensorType type;
  @ManyToOne
  @JoinColumn(name = "station_id")
  private Station station;
  @OneToMany(mappedBy = "sensor")
  private List<SensorData> data;
}
