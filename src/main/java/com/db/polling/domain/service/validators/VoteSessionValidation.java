package com.db.polling.domain.service.validators;

import com.db.polling.database.entity.AgendaEntity;

public interface VoteSessionValidation {

  void validate(AgendaEntity agendaEntity);

}
