package com.ikram.pfe.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Role {

  @Id
  @GeneratedValue
  private Integer id;
  @Enumerated(EnumType.STRING)
  private RoleNames role;
  @ManyToMany(mappedBy = "roles")
  @JsonIgnore
  private List<AppUser> user;

  public Role(RoleNames role) {
    this.role = role;
  }
}
