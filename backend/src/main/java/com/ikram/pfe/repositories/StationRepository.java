package com.ikram.pfe.repositories;

import com.ikram.pfe.models.Station;
import java.util.Collection;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StationRepository extends JpaRepository<Station, Integer> {

  Collection<Station> findAllByLandId(Integer landId);
}
