package com.ikram.pfe.exceptions;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import java.util.Set;
import lombok.Builder;
import lombok.Data;


@Data
@Builder
@JsonInclude(Include.NON_EMPTY)
public class ExceptionRepresentation {

  private String errorMessage;

  private String errorSource;

  private Set<String> validationErrors;

}
