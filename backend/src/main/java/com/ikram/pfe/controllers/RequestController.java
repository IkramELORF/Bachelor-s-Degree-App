package com.ikram.pfe.controllers;

import com.ikram.pfe.dto.RequestDto;
import com.ikram.pfe.models.RequestStatus;
import com.ikram.pfe.services.RequestService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/requests")
@RequiredArgsConstructor
public class RequestController {

  private final RequestService service;


  @PostMapping("/")
  public ResponseEntity<String> createRequest(
      @RequestBody RequestDto request
  ) {
    service.createRequest(request);
    return ResponseEntity.ok("Request successfully created");
  }

  @GetMapping("/{user-id}")
  public ResponseEntity<List<RequestDto>> allRequestsByUser(
      @PathVariable("user-id") Integer userId
  ) {
    return ResponseEntity.ok(service.findAllRequestsByUser(userId));
  }

  @GetMapping("/status/created")
  @PreAuthorize("hasRole('ADMIN')")
  public ResponseEntity<List<RequestDto>> allCreatedRequests() {
    return ResponseEntity.ok(service.findAllRequestsByStatus(RequestStatus.CREATED));
  }

  @GetMapping("/status/approved")
  @PreAuthorize("hasRole('ADMIN')")
  public ResponseEntity<List<RequestDto>> allApprovedRequests() {
    return ResponseEntity.ok(service.findAllRequestsByStatus(RequestStatus.APPROVED));
  }

  @GetMapping("/status/in-progress")
  @PreAuthorize("hasRole('ADMIN')")
  public ResponseEntity<List<RequestDto>> allInProgressRequests() {
    return ResponseEntity.ok(service.findAllRequestsByStatus(RequestStatus.SETUP_IN_PROGRESS));
  }

  @GetMapping("/status/validated")
  @PreAuthorize("hasRole('ADMIN')")
  public ResponseEntity<List<RequestDto>> allValidatedRequests() {
    return ResponseEntity.ok(service.findAllRequestsByStatus(RequestStatus.VALIDATED));
  }

  @GetMapping("/status/declined")
  @PreAuthorize("hasRole('ADMIN')")
  public ResponseEntity<List<RequestDto>> allDeclinedRequests() {
    return ResponseEntity.ok(service.findAllRequestsByStatus(RequestStatus.DECLINED));
  }

  @PatchMapping("/{request-id}")
  @PreAuthorize("hasRole('ADMIN')")
  public ResponseEntity<String> updateRequestStatus(
      @PathVariable("request-id") Integer requestId,
      @RequestParam("status") RequestStatus status
  ) {
    service.updateRequestStatus(requestId, status);
    return ResponseEntity.ok("Request was successfully updated");
  }

}
