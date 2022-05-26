package io.github.diegoss.api.services.impl;

import io.github.diegoss.api.domain.User;
import io.github.diegoss.api.domain.dto.UserDTO;
import io.github.diegoss.api.repository.UserRepository;
import io.github.diegoss.api.services.UserService;
import io.github.diegoss.api.services.exceptions.DataIntegrityViolationException;
import io.github.diegoss.api.services.exceptions.ObjectNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    ModelMapper mapper;

    @Override
    public User findById(Integer id) {
        return userRepository.findById(id)
                .orElseThrow(()-> new ObjectNotFoundException("Failed to find User by id " + id));
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User create(UserDTO obj) {
        findByEmail(obj);
        var user = mapper.map(obj, User.class);
        return userRepository.save(user);
    }

    private void findByEmail(UserDTO obj){
        var user = userRepository.findByEmail(obj.getEmail());

        if(user.isPresent()){
            throw new DataIntegrityViolationException("E-mail já cadastrado no sistema!");
        }
    }
}
