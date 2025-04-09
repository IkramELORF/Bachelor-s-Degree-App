package com.ikram.pfe.services;

import com.ikram.pfe.dto.RequestDto;
import com.ikram.pfe.mappers.ObjectsMapper;
import com.ikram.pfe.models.Land;
import com.ikram.pfe.models.Request;
import com.ikram.pfe.models.RequestStatus;
import com.ikram.pfe.repositories.LandRepository;
import com.ikram.pfe.repositories.RequestRepository;
import com.ikram.pfe.validators.ClassValidator;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RequestService {

  private final RequestRepository repository;
  private final LandRepository landRepository;
  private final ClassValidator<RequestDto> validator;
  private final ObjectsMapper mapper;

  @Transactional
  public RequestDto createRequest(RequestDto request) {
    validator.validate(request);
    Request requestToSave = mapper.toRequest(request);
    Land savedLand = landRepository.save(
        mapper.landFromRequest(request)
    );
    requestToSave.setLand(savedLand);
    requestToSave.setStatus(RequestStatus.CREATED);
    requestToSave.setRequestDate(LocalDateTime.now());
    Request savedRequest = repository.save(requestToSave);
    return mapper.fromRequest(savedRequest);
  }

  public List<RequestDto> findAllRequestsByStatus(RequestStatus status) {
    return repository.findAllByStatus(status).stream()
        .map(mapper::fromRequest)
        .collect(Collectors.toList());
  }

  public List<RequestDto> findAllRequestsByUser(Integer userId) {
    return repository.findAllByUserId(userId).stream()
        .map(mapper::fromRequest)
        .collect(Collectors.toList());
  }

  public RequestDto updateRequestStatus(Integer requestId, RequestStatus status) {
    Request request = repository.findById(requestId).orElseThrow(() -> new EntityNotFoundException("No request found with ID " + requestId));
    request.setStatus(status);
    request.setLastUpdated(LocalDateTime.now());
    return mapper.fromRequest(
        repository.save(request)
    );
  }
}
