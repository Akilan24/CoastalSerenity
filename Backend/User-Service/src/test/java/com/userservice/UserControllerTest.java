package com.userservice;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.userservice.controller.UserController;
import com.userservice.entity.Registration;
import com.userservice.service.UserService;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {

	@Mock
	private UserService userService;
	@InjectMocks
	private UserController userController;

	@Test
	void testListUser() {

		List<Registration> userList = Arrays.asList(new Registration("Joe123", "Johnjoe", "johnjoe@example.com",
				"Password@111", "user", "9876543210", "123 Main Street", "male", "Single", null));
		when(userService.ShowAllUser()).thenReturn(userList);

		ResponseEntity<List<Registration>> response = userController.listAllUser();

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(userList, response.getBody());
	}

	@Test
	void testUpdateUser() {
		String username = "Joe123";

		Registration user = new Registration("Joe123", "Johnjoe", "johnjoe@example.com", "Password@111", "user",
				"9876543210", "123 Main Street", "male", "Single", null);
		when(userService.updateUserByUserName(username, user)).thenReturn(user);

		ResponseEntity<Registration> response = userController.updateUserByUsername(username, user);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(user, response.getBody());
	}

	@Test
	void testUpdatePassword() {
		String username = "Joe123";
		String newPassword = "NewPassword@123";
		String expectedMessage = "Password updated successfully for username: " + username;
		when(userService.updateUserPasswordByUserName(username, newPassword)).thenReturn(expectedMessage);

		ResponseEntity<String> response = userController.updatePasswordByUsername(username, newPassword);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(expectedMessage, response.getBody());
	}

	@Test
	void testShowUserByUserName() {
		String username = "Joe123";

		Registration user = new Registration("Joe123", "Johnjoe", "johnjoe@example.com", "Password@111", "user",
				"9876543210", "123 Main Street", "male", "Single", null);
		when(userService.ShowUserByUserName(username)).thenReturn(user);

		ResponseEntity<Registration> response = userController.showUserByUserName(username);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(user, response.getBody());
	}

	@Test
	void testShowUserByEmail() {
		String email = "johnjoe@example.com";

		Registration user = new Registration("Joe123", "Johnjoe", "johnjoe@example.com", "Password@111", "user",
				"9876543210", "123 Main Street", "male", "Single", null);
		when(userService.ShowUserByEmail(email)).thenReturn(user);

		ResponseEntity<Registration> response = userController.showUserByEmail(email);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(user, response.getBody());
	}

	@Test
	void testShowUserByMobileNumber() {
		String mobile = "9876543210";

		Registration user = new Registration("Joe123", "Johnjoe", "johnjoe@example.com", "Password@111", "user",
				"9876543210", "123 Main Street", "male", "Single", null);
		when(userService.ShowUserByMobileNumber(mobile)).thenReturn(user);

		ResponseEntity<Registration> response = userController.showUserByMobileNumber(mobile);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(user, response.getBody());
	}

	@Test
	void testRemove() {
		String username = "Joe123";
		String expectedMessage = "User removed successfully with username: " + username;
		when(userService.removeUserByUserName(username)).thenReturn(expectedMessage);

		ResponseEntity<String> response = userController.deleteUserByUsername(username);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(expectedMessage, response.getBody());
	}
}
