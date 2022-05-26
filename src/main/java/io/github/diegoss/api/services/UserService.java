package io.github.diegoss.api.services;


import io.github.diegoss.api.domain.User;
import io.github.diegoss.api.domain.dto.UserDTO;

import java.util.List;

public interface UserService {

    User findById(Integer id);
    List<User> findAll();
    User create(UserDTO obj);
}
