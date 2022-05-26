package io.github.diegoss.api.services;


import io.github.diegoss.api.domain.User;

import java.util.List;

public interface UserService {

    User findById(Integer id);

    List<User> findAll();
}
