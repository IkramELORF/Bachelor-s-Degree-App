package com.ikram.pfe.services;

import com.ikram.pfe.dto.LandDto;
import com.ikram.pfe.mappers.ObjectsMapper;
import com.ikram.pfe.repositories.LandRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LandService {

  private final LandRepository repository;
  private final ObjectsMapper mapper;


  public List<LandDto> findAllByUser(Integer userId) {
    return repository.findAllByUserId(userId).stream()
        .map(mapper::fromLand)
        .collect(Collectors.toList());
  }
}
