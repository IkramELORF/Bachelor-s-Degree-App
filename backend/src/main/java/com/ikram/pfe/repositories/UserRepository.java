package com.ikram.pfe.repositories;

import com.ikram.pfe.models.AppUser;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<AppUser, Integer> {

  Optional<AppUser> findByEmail(String email);

}
