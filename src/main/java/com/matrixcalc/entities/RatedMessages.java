package com.matrixcalc.entities;

import com.matrixcalc.entities.compositekeys.RatedMessagesKeys;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity
public class RatedMessages {
    @EmbeddedId
    private RatedMessagesKeys id;

    public RatedMessagesKeys getId() {
        return id;
    }

    public void setId(RatedMessagesKeys id) {
        this.id = id;
    }
}
