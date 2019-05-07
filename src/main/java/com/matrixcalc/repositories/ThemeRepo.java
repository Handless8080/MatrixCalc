package com.matrixcalc.repositories;

import com.matrixcalc.entities.Theme;
import org.springframework.data.repository.CrudRepository;

public interface ThemeRepo extends CrudRepository<Theme, Long> {
}
