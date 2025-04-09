package com.ikram.pfe.controllers;

import com.ikram.pfe.dto.LandDto;
import com.ikram.pfe.services.LandService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/lands")
@RequiredArgsConstructor
public class LandController {


  private final LandService service;

  @GetMapping("/{user-id}")
  public ResponseEntity<List<LandDto>> findAllByUser(
      @PathVariable("user-id") Integer userId
  ) {
    return ResponseEntity.ok(service.findAllByUser(userId));
  }
}
