package io.github.diegoss.api.resources;

import io.github.diegoss.api.domain.User;
import io.github.diegoss.api.domain.dto.UserDTO;
import io.github.diegoss.api.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
class UserResourceTest {

    public static final Integer ID = 1;
    public static final String NAME = "mock";
    public static final String EMAIL = "mock@email.com";
    public static final String PASSWORD = "123";

    @InjectMocks
    private UserResource userResource;

    @Mock
    private UserService userService;

    @Mock
    private ModelMapper mapper;

    private User user;
    private UserDTO userDTO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        userDTO = new UserDTO(ID, NAME, EMAIL, PASSWORD);
        user = new User(ID, NAME, EMAIL, PASSWORD);
    }

    @Test
    void whenFindByIdThenReturnUserDTO() {
        when(userService.findById(any())).thenReturn(user);
        when(mapper.map(any(), any())).thenReturn(userDTO);

        var response = userResource.findById(1);

        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(UserDTO.class, response.getBody().getClass());
        assertEquals(ID, userDTO.getId());
    }

    @Test
    void deleteById() {
    }

    @Test
    void whenFindAllThenReturnUserDTOList() {
        when(userService.findAll()).thenReturn(List.of(user));
        when(mapper.map(any(), any())).thenReturn(userDTO);

        var response = userResource.findAll();

        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(user.getId(), response.getBody().get(0).getId());
        assertInstanceOf(Collection.class, response.getBody());
    }

    @Test
    void whenCreateThenReturnUserDTO() {
        when(userService.create(any())).thenReturn(user);
        var response = userResource.create(userDTO);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(ResponseEntity.class, response.getClass());
        assertNotNull(response.getHeaders().get("Location"));
        assertTrue(response.getHeaders().get("Location").toString().contains(userDTO.getId().toString()));
    }

    @Test
    void update() {
    }
}