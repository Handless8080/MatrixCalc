package com.matrixcalc.entities;

import com.matrixcalc.entities.compositekeys.RatedThemesKey;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity
public class RatedThemes {
    @EmbeddedId
    private RatedThemesKey id;

    public RatedThemesKey getId() {
        return id;
    }

    public void setId(RatedThemesKey id) {
        this.id = id;
    }
}
