package com.userservice.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.userservice.entity.Registration;
import com.userservice.entity.Traveller;

@Service
public interface UserService {

	public String removeUser(String username);

	public List<Registration> ShowAllUser();

	public Registration ShowUserByUserName(String username);

	public Registration ShowUserByEmail(String email);

	public Registration ShowUserByMobileNumber(String mobilenumber);

	public Registration updateUser(String username, Registration user);

	public String updateUserpasswordbyusername(String username, String password);

	public List<Traveller> getTravellerByUsername(String username);

	public String addtraveller(String username, Traveller t);

	public String updatetraveller(String username, Traveller t);
    
	public Traveller gettraveller(String username, String name);
	
	public String deletetraveller(String username, String name);

}
