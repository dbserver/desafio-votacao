package com.db.polling.api.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public abstract class AbstractHeaderDTO {

  private boolean hasNext;
  private int totalPages;
  private int totalElements;

}
