package com.matrixcalc.repositories;

import com.matrixcalc.entities.RatedThemes;
import com.matrixcalc.entities.compositekeys.RatedThemesKey;
import org.springframework.data.repository.CrudRepository;

public interface RatedThemesRepo extends CrudRepository<RatedThemes, RatedThemesKey> {
}
