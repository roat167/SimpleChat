package com.orainteractive.simplechat.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringRunner;

import com.orainteractive.simplechat.domain.User;
import com.orainteractive.simplechat.exception.DuplicateUsernameException;
import com.orainteractive.simplechat.repository.UserRepository;

@RunWith(SpringRunner.class)
public class UserServiceTest {
	@Mock
	private UserRepository userRepository;

	@InjectMocks
	private UserService userService = new UserServiceImpl();
	
	/**
	 * using BeforeClass will execute the test once before other Tests
	 */
    @BeforeClass
    public static void setUp() {     
    	MockitoAnnotations.initMocks(UserServiceTest.class);	    	
    }

	@Test
	public void findAll() {
		List<User> users = new ArrayList<>();
		users.add(new User(1L, "jim", "password", "Josh", "Impossible", "cleang@mum.edu"));
		users.add(new User(2L, "otwist", "password", "Oliver", "Twist", "test@mum.edu"));
		users.add(new User(3L, "sholmes", "password", "Sherklock", "Holmes", "kloem@mum.edu"));
		when(userRepository.findAll()).thenReturn(users);

		assertEquals(3, userService.getAll().size());
	}

	@Test
	public void findsByUsername() throws DuplicateUsernameException {
		String username = "abc";
		User user = new User(1L, username, "password", "Kora", "Avenger", "test@mum.edu");
		userService.save(user);

		verify(userRepository, times(1)).save(user);
		when(userService.getByUsername(username)).thenReturn(user);

		User result = userService.getByUsername(username);
		assertThat(result).extracting("firstName").contains("Kora");
	}

	@Test
	public void findById() throws DuplicateUsernameException {
		User user = new User(1L, "jim", "password", "Josh", "Impossible", "cleang@mum.edu");
		userService.save(user);
		verify(userRepository, times(1)).save(user);
		when(userService.getById(1L)).thenReturn(user);

		User uzer = userService.getById(1L);
		assertEquals(1, uzer.getId().longValue());
		assertEquals("Impossible", uzer.getLastName());
	}

	@Test
	public void deleteUserById() {
		User user = new User(1L, "kloem", "password", "khemroat", "loem", "kloem@mum.edu");
		userService.remove(user.getId());
		verify(userRepository, times(1)).delete(user.getId());
	}
}
