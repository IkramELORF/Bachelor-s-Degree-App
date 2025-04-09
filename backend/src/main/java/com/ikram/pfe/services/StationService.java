package com.ikram.pfe.services;

import com.ikram.pfe.dto.StationDto;
import com.ikram.pfe.mappers.ObjectsMapper;
import com.ikram.pfe.repositories.StationRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StationService {

  private final StationRepository repository;
  private final ObjectsMapper mapper;

  public List<StationDto> findAllByLand(Integer landId) {
    return repository.findAllByLandId(landId).stream()
        .map(mapper::fromStation)
        .collect(Collectors.toList());
  }
}
