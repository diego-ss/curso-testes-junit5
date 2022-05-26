package io.github.diegoss.api.services.impl;

import io.github.diegoss.api.domain.User;
import io.github.diegoss.api.repository.UserRepository;
import io.github.diegoss.api.services.UserService;
import io.github.diegoss.api.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public User findById(Integer id) {
        return userRepository.findById(id)
                .orElseThrow(()-> new ObjectNotFoundException("Failed to find User by id " + id));
    }
}
