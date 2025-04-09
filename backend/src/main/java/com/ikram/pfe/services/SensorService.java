package com.ikram.pfe.services;

import com.ikram.pfe.dto.SensorDataDto;
import com.ikram.pfe.dto.SensorDto;
import com.ikram.pfe.mappers.ObjectsMapper;
import com.ikram.pfe.models.SensorType;
import com.ikram.pfe.repositories.SensorDataRepository;
import com.ikram.pfe.repositories.SensorRepository;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SensorService {

  private final SensorRepository repository;
  private final SensorDataRepository dataRepository;
  private final ObjectsMapper mapper;


  public List<SensorDto> findAllByStation(Integer landId) {
    return repository.findAllByStationId(landId).stream()
        .map(mapper::fromSensor)
        .collect(Collectors.toList());
  }

  public List<SensorDataDto> findAllSensorDataBySensor(
      Integer sensorId,
      LocalDate start,
      LocalDate end
  ) {
    LocalDateTime startDate = LocalDateTime.of(start.getYear(), start.getMonth(), start.getDayOfMonth(), 0, 0, 0);
    LocalDateTime endDate = LocalDateTime.of(start.getYear(), start.getMonth(), start.getDayOfMonth(), 23, 59, 59);
    return dataRepository.findAllBySensorIdAndDateBetween(
        sensorId,
        startDate,
        endDate
        ).stream()
        .map(mapper::fromSensorData)
        .collect(Collectors.toList());
  }

  public List<SensorDataDto> findAllSensorDataBySensorType(SensorType type) {
    return dataRepository.findAllBySensorType(type).stream()
        .map(mapper::fromSensorData)
        .collect(Collectors.toList());
  }
}
