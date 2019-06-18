package com.matrixcalc.repositories;

import com.matrixcalc.entities.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepo extends JpaRepository<Message, Long> {
}
