package com.ikram.pfe.models;

import java.util.List;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.userdetails.User;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Land {

  @Id
  @GeneratedValue
  private Integer id;
  private String displayName;
  @Embedded
  private GeoLocation location;
  @ManyToOne
  @JoinColumn(name = "userId")
  private AppUser user;
  @OneToMany(mappedBy = "land")
  private List<Station> stations;
}
