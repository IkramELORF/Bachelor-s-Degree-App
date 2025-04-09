package com.ikram.pfe.models;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Request {

  @Id
  @GeneratedValue
  private Integer id;
  private String title;
  @Column(length = 1024)
  private String description;
  private LocalDateTime requestDate;
  private LocalDateTime lastUpdated;
  @Enumerated(EnumType.STRING)
  private RequestStatus status;
  @ManyToOne
  @JoinColumn(name = "user_id")
  private AppUser user;
  @ManyToOne
  @JoinColumn(name = "land_id")
  private Land land;
}
