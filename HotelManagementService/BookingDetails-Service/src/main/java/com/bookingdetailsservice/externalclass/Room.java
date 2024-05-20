package com.bookingdetailsservice.externalclass;



import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Room {

	@Id
	private long roomId;
	@Pattern(regexp = "^\\d{3}$\n", message = "Please provide a valid room number")
	private int room_no;
	@NotNull
	private Double rate_per_day;
	@Pattern(regexp = "^(Single Room|Standard Twin Room|Standard Double Room|Deluxe Double Room|Triple room|Quad room|Suite)$", message = "Please provide a valid room type")
	private String roomtype;

	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hotelId", referencedColumnName = "hotelId", insertable = false, updatable = false)
    private Hotel hotel;
}
