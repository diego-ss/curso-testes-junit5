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
    void findById() {
    }

    @Test
    void deleteById() {
    }

    @Test
    void findAll() {
    }

    @Test
    void create() {
    }

    @Test
    void update() {
    }
}