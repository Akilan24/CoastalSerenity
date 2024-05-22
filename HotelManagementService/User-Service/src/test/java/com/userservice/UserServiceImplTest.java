package com.userservice;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.userservice.entity.Registration;
import com.userservice.exception.UserDetailsNotFoundException;
import com.userservice.repo.UserRepository;
import com.userservice.service.UserServiceImpl;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

	@Mock
	private UserRepository userRepository;

	@Mock
	private PasswordEncoder passwordEncoder;

	@InjectMocks
	private UserServiceImpl userService;

	@Test
	void testUpdateUser() {
		String username = "Joe123";

		Registration user = new Registration(username, "John12", "john@example.com", "password", "user", "1234567890",
				"Address", "male", "Single", null);
		Registration updatedUser = new Registration(username, "John12", "newemail@example.com", "password", "user",
				"9876543210", "New Address", "male", "Single", null);
		when(userRepository.existsById(user.getUsername())).thenReturn(true);
		when(userRepository.findById(user.getUsername())).thenReturn(Optional.of(user));
		when(userRepository.save(updatedUser)).thenReturn(updatedUser);

		Registration result = userService.updateUser(user.getUsername(), updatedUser);

		assertEquals(updatedUser.getAddress(), result.getAddress());
	}

	@Test
	void testUpdateUser_UserNotFound() {

		Optional<Registration> user = Optional.of(new Registration("Joe123", "Johnjoe", "johnjoe@example.com",
				"Password@111", "user", "9876543210", "123 Main Street", "male", "Single", null));
		when(userRepository.existsById(user.get().getUsername())).thenReturn(false);

		assertThrows(UserDetailsNotFoundException.class,
				() -> userService.updateUser(user.get().getUsername(), user.get()));
	}

	@Test
	void testRemoveUser() {
		String username = "Joe123";
		when(userRepository.existsById(username)).thenReturn(true);

		String result = userService.removeUser(username);

		assertEquals("User details are deleted", result);
	}

	@Test
	void testRemoveUser_UserNotFound() {
		String username = "Joe123";
		when(userRepository.existsById(username)).thenReturn(false);

		assertThrows(UserDetailsNotFoundException.class, () -> userService.removeUser(username));
	}

	@Test
	void testShowAllUser() {

		List<Registration> userList = Arrays.asList(
				new Registration("Joe123", "Johnjoe", "johnjoe@example.com", "Password@111", "user", "9876543210",
						"123 Main Street", "male", "Single", null),
				new Registration("Joe124", "Johnjoe", "johnjoe@example.com", "Password@111", "user", "9876543210",
						"123 Main Street", "male", "Single", null));
		when(userRepository.findAll()).thenReturn(userList);

		List<Registration> result = userService.ShowAllUser();

		assertEquals(userList, result);
	}

	@Test
	void testShowAllUser_NoUsersFound() {
		when(userRepository.findAll()).thenReturn(Arrays.asList());

		assertThrows(UserDetailsNotFoundException.class, () -> userService.ShowAllUser());
	}

	@Test
	void testShowUserByusername() {
		String username = "testUser";
		Registration user = new Registration(username, "John", "john@example.com", "password", "user", "1234567890",
				"Address", "male", "Single", null);

		when(userRepository.findByUsername(username)).thenReturn(Optional.of(user));

		Registration retrievedUser = userService.ShowUserByUserName(username);

		assertEquals(user, retrievedUser);
	}

	@Test
	void testShowUserByUserName_UserNotFound() {
		String username = "nonExistentUser";

		when(userRepository.findByUsername(username)).thenReturn(Optional.empty());

		assertThrows(UserDetailsNotFoundException.class, () -> {
			userService.ShowUserByUserName(username);
		});
	}

	@Test
	void testShowUserByEmail() {
		String email = "john@example.com";

		Registration user = new Registration("Joe123", "Johnjoe", "johnjoe@example.com", "Password@111", "user",
				"9876543210", "123 Main Street", "male", "Single", null);
		when(userRepository.findByEmail(email)).thenReturn(Optional.of(user));

		Registration result = userService.ShowUserByEmail(email);

		assertEquals(user, result);
	}

	@Test
	void testShowUserByEmail_UserNotFound() {
		String email = "john@example.com";
		when(userRepository.findByEmail(email)).thenReturn(Optional.empty());

		assertThrows(UserDetailsNotFoundException.class, () -> userService.ShowUserByEmail(email));
	}

	@Test
	void testShowUserByMobileNumber() {
		String mobile = "9234567890";

		Registration user = new Registration("Joe123", "Johnjoe", "johnjoe@example.com", "Password@111", "user",
				"9876543210", "123 Main Street", "male", "Single", null);
		when(userRepository.findByMobile(mobile)).thenReturn(Optional.of(user));

		Registration result = userService.ShowUserByMobileNumber(mobile);

		assertEquals(user, result);
	}

	@Test
	void testShowUserByMobileNumber_UserNotFound() {
		String mobile = "9234567890";
		when(userRepository.findByMobile(mobile)).thenReturn(Optional.empty());

		assertThrows(UserDetailsNotFoundException.class, () -> userService.ShowUserByMobileNumber(mobile));
	}

	@Test
	void testUpdateUserPasswordByUsername_UserExists() {
		String username = "testUser";
		String newPassword = "newPassword";
		String encodedPassword = "encodedPassword";

		Registration user = new Registration(username, "John", "john@example.com", "password", "user", "1234567890",
				"Address", "male", "Single", null);

		when(userRepository.findById(username)).thenReturn(Optional.of(user));
		when(passwordEncoder.encode(newPassword)).thenReturn(encodedPassword);

		String result = userService.updateUserpasswordbyusername(username, newPassword);

		assertEquals("User password updated", result);
		assertEquals(encodedPassword, user.getPassword());
	}

	@Test
	void testUpdateUserPasswordByUsername_UserNotFound() {
		String username = "nonExistentUser";
		String newPassword = "newPassword";

		when(userRepository.findById(username)).thenReturn(Optional.empty());

		assertThrows(UserDetailsNotFoundException.class, () -> {
			userService.updateUserpasswordbyusername(username, newPassword);
		});
	}

	@Test
	void testUpdateUserPasswordById_UserNotFound() {
		String username = "Joe123";
		String newPassword = "newpassword";
		when(userRepository.findById(username)).thenReturn(Optional.empty());

		assertThrows(UserDetailsNotFoundException.class,
				() -> userService.updateUserpasswordbyusername(username, newPassword));
	}

}
