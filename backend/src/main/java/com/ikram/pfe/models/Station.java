package com.ikram.pfe.models;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import lombok.Data;

@Data
@Entity
public class Station {

  @Id
  @GeneratedValue
  private Integer id;
  private String name;
  private String registrationNumber;
  @ManyToOne
  @JoinColumn(name = "land_id")
  private Land land;
  @OneToMany(mappedBy = "station")
  private List<Sensor> sensors;
}
