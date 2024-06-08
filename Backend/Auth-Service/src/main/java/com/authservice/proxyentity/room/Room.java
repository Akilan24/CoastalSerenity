package com.authservice.proxyentity.room;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Room {

	private long roomId;
	@Min(value = 100, message = "Please provide a valid room number")
	@Max(value = 999, message = "Please provide a valid room number")
	private int room_no;
	@NotNull
	private Double rate_per_day;
	@Pattern(regexp = "^(Single Room|Standard Twin Room|Standard Double Room|Deluxe Double Room|Triple Room|Quad Room|Suite)$", message = "Please provide a valid room type")
	private String roomtype;
	private String roomImage;

}
