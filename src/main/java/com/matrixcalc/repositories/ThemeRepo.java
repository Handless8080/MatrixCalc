package com.matrixcalc.repositories;

import com.matrixcalc.entities.Theme;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ThemeRepo extends JpaRepository<Theme, Long> {
    List<Theme> findByCategoryOrderByRateDesc(String category);
}
