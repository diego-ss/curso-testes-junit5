package io.github.diegoss.api.resources;

import io.github.diegoss.api.domain.User;
import io.github.diegoss.api.domain.dto.UserDTO;
import io.github.diegoss.api.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
public class UserResource {

    @Autowired
    private UserService userService;

    @Autowired
    private ModelMapper mapper;

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> findById(@PathVariable Integer id) {
        return ResponseEntity.ok().body(mapper.map(userService.findById(id), UserDTO.class));
    }

    @GetMapping
    public ResponseEntity<List<UserDTO>> findAll(){
        var list = userService.findAll();
        var mappedList =
                list.stream().map(u -> mapper.map(u, UserDTO.class)).collect(Collectors.toList());
        return ResponseEntity.ok().body(mappedList);
    }

    @PostMapping
    public ResponseEntity<UserDTO> create(@RequestBody UserDTO obj) {
        User user = userService.create(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(user.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }
}
