package io.github.diegoss.api.services;


import io.github.diegoss.api.domain.User;

public interface UserService {

    User findById(Integer id);
}
