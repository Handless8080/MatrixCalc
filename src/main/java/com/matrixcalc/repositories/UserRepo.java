package com.matrixcalc.repositories;

import com.matrixcalc.entities.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepo extends CrudRepository<User, Long> {
    User findByUsername(String username);

    User findByEmail(String email);

    User findByActivationCode(String code);
    User findByDeactivationCode(String code);
    User findByPasswordChangeCode(String code);
}
