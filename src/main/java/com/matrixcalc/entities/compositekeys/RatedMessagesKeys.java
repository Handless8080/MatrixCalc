package com.matrixcalc.entities.compositekeys;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class RatedMessagesKeys implements Serializable {
    private Long userId;
    private Long messageId;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getMessageId() {
        return messageId;
    }

    public void setMessageId(Long messageId) {
        this.messageId = messageId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUserId(), getMessageId());
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof RatedMessagesKeys)) {
            return false;
        }

        RatedMessagesKeys o = (RatedMessagesKeys) obj;

        return Objects.equals(getMessageId(), o.getMessageId()) && Objects.equals(getUserId(), o.getUserId());
    }
}
