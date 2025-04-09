package com.ikram.pfe.repositories;

import com.ikram.pfe.models.Land;
import java.util.Collection;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LandRepository extends JpaRepository<Land, Integer> {

  Collection<Land> findAllByUserId(Integer userId);
}
