package com.ikram.pfe.repositories;

import com.ikram.pfe.models.Request;
import com.ikram.pfe.models.RequestStatus;
import java.util.Collection;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RequestRepository extends JpaRepository<Request, Integer> {

  Collection<Request> findAllByStatus(RequestStatus status);

  Collection<Request> findAllByUserId(Integer userId);
}
