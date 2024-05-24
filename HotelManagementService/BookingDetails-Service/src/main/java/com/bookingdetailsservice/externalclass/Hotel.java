package com.bookingdetailsservice.externalclass;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
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
public class Hotel {

	@Id
	private long hotelId;
	@NotBlank(message = "Please provide a City name")
	private String city;
	@NotBlank(message = "Please provide a Hotel name")
	private String hotelName;
	@NotBlank(message = "Please provide a Address")
	private String address;
	@NotBlank(message = "Please provide a Description about Hotel")
	private String description;
	@Email(message = "Please provide a valid email address")
	private String email;
	@Pattern(regexp = "^[6-9]\\d{9}$", message = "Mobile number must start with 6, 7, 8, or 9 and have a maximum of 10 digits")
	private String mobile1;
	@Pattern(regexp = "^[6-9]\\d{9}$", message = "Mobile number must start with 6, 7, 8, or 9 and have a maximum of 10 digits")
	private String mobile2;
	@Pattern(regexp = "^(https?://)?(www\\.)?[a-zA-Z0-9-]+\\.[a-zA-Z]{2,}(\\.[a-zA-Z]{2,})?$", message = "Please provide a valid website")
	private String website;
	private String hotelImage;
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "hotelId", referencedColumnName = "hotelId")
	private List<Room> rooms;
}
