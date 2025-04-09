package com.ikram.pfe.repositories;

import com.ikram.pfe.models.Role;
import com.ikram.pfe.models.RoleNames;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Integer> {

  Optional<Role> findByRole(RoleNames roleUser);
}
