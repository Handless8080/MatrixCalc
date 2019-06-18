package com.matrixcalc.repositories;

import com.matrixcalc.entities.RatedMessages;
import com.matrixcalc.entities.compositekeys.RatedMessagesKeys;
import org.springframework.data.repository.CrudRepository;

public interface RatedMessagesRepo extends CrudRepository<RatedMessages, RatedMessagesKeys> {
}
