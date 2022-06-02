package io.github.diegoss.api.services.impl;

import io.github.diegoss.api.domain.User;
import io.github.diegoss.api.domain.dto.UserDTO;
import io.github.diegoss.api.repository.UserRepository;
import io.github.diegoss.api.services.exceptions.DataIntegrityViolationException;
import io.github.diegoss.api.services.exceptions.ObjectNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
class UserServiceImplTest {

    public static final Integer ID = 1;
    public static final String NAME = "mock";
    public static final String EMAIL = "mock@email.com";
    public static final String PASSWORD = "123";

    @InjectMocks
    private UserServiceImpl service;

    @Mock
    private UserRepository repository;

    @Mock
    private ModelMapper mapper;

    private User user;
    private UserDTO userDTO;
    private Optional<User> optionalUser;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        user = new User(ID, NAME, EMAIL, PASSWORD);
        userDTO = new UserDTO(ID, NAME, EMAIL, PASSWORD);
        optionalUser = Optional.of(new User(ID, NAME, EMAIL, PASSWORD));
    }

    @Test
    void whenFindByIdThenReturnAnUserInstance() {
        when(repository.findById(any())).thenReturn(optionalUser);
        var response = service.findById(ID);

        assertNotNull(response);
        assertEquals(User.class, response.getClass());
        assertEquals(ID, response.getId());
        assertEquals(NAME, response.getName());
        assertEquals(EMAIL, response.getEmail());

    }

    @Test
    void whenFindByIdThenReturnObjectNotFoundException() {
        when(repository.findById(any())).thenThrow(new ObjectNotFoundException("Failed to find User"));

        try{
            service.findById(ID);
        } catch (Exception ex){
            assertEquals(ObjectNotFoundException.class, ex.getClass());
            assertEquals("Failed to find User", ex.getMessage());
        }
    }

    @Test
    void whenFindAllThenReturnUsersList() {
        when(repository.findAll()).thenReturn(List.of(user));
        var response = service.findAll();
        assertEquals(1, response.size());
        assertNotNull(response);
        assertInstanceOf(Collection.class, response);
    }

    @Test
    void whenCreateThenReturnUserWithId() {
        when(repository.save(any())).thenReturn(user);

        User response = service.create(userDTO);

        assertNotNull(response);
        assertEquals(ID, response.getId());
        assertEquals(User.class, user.getClass());
    }

    @Test
    void whenCreateThenReturnDataIntegritViolationException() {
        when(repository.findByEmail(any())).thenReturn(optionalUser);

        try{
            userDTO.setId(2);
            User response = service.create(userDTO);
        } catch (Exception ex){
            assertNotNull(ex);
            assertEquals(DataIntegrityViolationException.class, ex.getClass());
        }
    }

    @Test
    void whenUpdateThenReturnSuccess() {
        when(repository.save(any())).thenReturn(user);

        User response = service.update(userDTO);

        assertNotNull(response);
        assertEquals(ID, response.getId());
        assertEquals(User.class, user.getClass());
    }

    @Test
    void delete() {
    }
}