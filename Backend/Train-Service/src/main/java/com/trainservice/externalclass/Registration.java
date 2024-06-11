package com.trainservice.externalclass;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Users")
public class Registration {

	@Id
	@Pattern(regexp = "^(?=.*\\d)[a-zA-Z0-9]{6}$", message = "Username should be alphanumeric and 6 characters long")
	private String username;
	@Pattern(regexp = "^[a-zA-Z]{3,30}$", message = "Please provide a Name")
	private String name;
	@Email(message = "Please provide a valid email address")
	private String email;
	@Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{8,}$", message = "Password must contain at least one digit, one lowercase and uppercase letter, one special character, and be at least 8 characters long")
	private String password;
	@Pattern(regexp = "^(user|admin)$", message = "Please provide a valid role")
	private String roles;
	@Pattern(regexp = "^[6-9]\\d{9}$", message = "Mobile number must start with 6, 7, 8, or 9 and have a maximum of 10 digits")
	private String mobile;
	@NotBlank(message = "Please provide an Address")
	private String address;
	@NotBlank(message = "Please provide a Gender")
	private String gender;
	@NotBlank(message = "Please provide a Marital Status")
	private String maritalStatus;
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "username", referencedColumnName = "username")
	private List<Traveller> traveller;
}
