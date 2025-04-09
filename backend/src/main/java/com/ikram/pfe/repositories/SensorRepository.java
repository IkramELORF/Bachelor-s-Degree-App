package com.ikram.pfe.repositories;

import com.ikram.pfe.models.Sensor;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SensorRepository extends JpaRepository<Sensor, Integer> {

  List<Sensor> findAllByStationId(Integer landId);
}
