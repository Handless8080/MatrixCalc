package com.matrixcalc.entities.compositekeys;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class RatedThemesKey implements Serializable {
    private Long userId;
    private Long themeId;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getThemeId() {
        return themeId;
    }

    public void setThemeId(Long themeId) {
        this.themeId = themeId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUserId(), getThemeId());
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof RatedThemesKey)) {
            return false;
        }

        RatedThemesKey o = (RatedThemesKey) obj;

        return Objects.equals(getThemeId(), o.getThemeId()) && Objects.equals(getUserId(), o.getUserId());
    }
}
