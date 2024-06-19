package com.userservice.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.userservice.entity.Registration;
import com.userservice.entity.Traveller;

@Service
public interface UserService {

	public String removeUserByUserName(String username);

	public List<Registration> ShowAllUser();

	public Registration ShowUserByUserName(String username);

	public Registration ShowUserByEmail(String email);

	public Registration ShowUserByMobileNumber(String mobilenumber);

	public Registration updateUserByUserName(String username, Registration user);

	public String updateUserPasswordByUserName(String username, String password);

	public List<Traveller> getAllTravellersByUsername(String username);

	public String addTravellerByUserName(String username, Traveller t);

	public String updateTravellerByUserName(String username, Traveller t);

	public Traveller getTravellerByTravellerName(String username, String name);

	public String deleteTravellerByTravellerName(String username, String name);

}
