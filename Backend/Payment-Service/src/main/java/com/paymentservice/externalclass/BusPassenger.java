package com.paymentservice.externalclass;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BusPassenger {


	private int busPassengerId;
	@Pattern(regexp = "^[a-zA-Z]{3,20}$", message = "Please provide a Name")
	private String name;
	@NotBlank(message = "Please provide a Gender")
	private String gender;
	private int age;
	@Pattern(regexp = "^[6-9]\\d{9}$", message = "Mobile number must start with 6, 7, 8, or 9 and have a maximum of 10 digits")
	private String mobile;
	@NotBlank(message = "Please provide an Address")
	private String address;
	private String seatNo;
	private String seatClass;
}