package com.ikram.pfe.controllers;

import com.ikram.pfe.dto.StationDto;
import com.ikram.pfe.services.StationService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/stations")
@RequiredArgsConstructor
public class StationController {

  private final StationService service;

  @GetMapping("/{land-id}")
  public ResponseEntity<List<StationDto>> findAllByLand(
      @PathVariable("land-id") Integer landId
  ) {
    return ResponseEntity.ok(service.findAllByLand(landId));
  }
}
