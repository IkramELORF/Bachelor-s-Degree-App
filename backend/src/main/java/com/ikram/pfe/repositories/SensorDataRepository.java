package com.ikram.pfe.repositories;

import com.ikram.pfe.models.SensorData;
import com.ikram.pfe.models.SensorType;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SensorDataRepository extends JpaRepository<SensorData, Integer> {

  List<SensorData> findAllBySensorIdAndDateBetween(Integer sensorId, LocalDateTime startDate, LocalDateTime endDate);

  List<SensorData> findAllBySensorType(SensorType type);
}
