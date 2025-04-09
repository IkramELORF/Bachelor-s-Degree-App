package com.ikram.pfe.controllers;

import com.ikram.pfe.dto.SensorDataDto;
import com.ikram.pfe.dto.SensorDto;
import com.ikram.pfe.models.SensorType;
import com.ikram.pfe.services.SensorService;
import java.time.LocalDate;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/sensors")
@RequiredArgsConstructor
public class SensorController {

  private final SensorService service;

  @GetMapping("/{station-id}")
  public ResponseEntity<List<SensorDto>> findAllByStation(
      @PathVariable("station-id") Integer landId
  ) {
    return ResponseEntity.ok(service.findAllByStation(landId));
  }

  @GetMapping("/data/{sensor-id}")
  public ResponseEntity<List<SensorDataDto>> findAllSensorDataBySensor(
      @PathVariable("sensor-id") Integer sensorId,
      @RequestParam("start-date") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate,
      @RequestParam("end-date") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate
  ) {
    return ResponseEntity.ok(service.findAllSensorDataBySensor(sensorId, startDate, endDate));
  }

  @GetMapping("/data")
  public ResponseEntity<List<SensorDataDto>> findAllSensorDataBySensorType(
      @RequestParam("sensor-type") SensorType type
  ) {
    return ResponseEntity.ok(service.findAllSensorDataBySensorType(type));
  }
}
