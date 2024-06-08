package com.authservice.proxyentity.flight;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FlightSeats {

    private long id;
    private String seatNumber;
    private String seatClass;
    private double price;
    private boolean available;

}
