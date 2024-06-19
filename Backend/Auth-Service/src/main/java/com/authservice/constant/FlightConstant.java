package com.authservice.constant;

public class FlightConstant {

	public static final String FLIGHT = "/CS/Flight";
	public static final String CROSS_ORIGIN = "http://localhost:5173";
	public static final String SERVICE = "FLIGHT-SERVICE";
	public static final String URL = "http://localhost:8086/Flight";
	public static final String GET_ALL_FLIGHT ="/getall";
	public static final String GET_FLIGHT_BY_FLIGHT_ID ="/getbyid/{id}";
	public static final String ADD_SEATS_BY_FLIGHT_ID ="/addseats/{id}";
	public static final String SAVE_FLIGHT ="/save";
	public static final String BOOK_FLIGHT_BY_FLIGHT_ID ="/bookflight/{id}/{username}";
	public static final String GET_FLIGHT_BOOKING_DETAILS_BY_FLIGHT_BOOKING_ID ="/getflightbookingdetailsbyid/{id}";
	public static final String GET_FLIGHT_BOOKING_DETAILS_BY_USERNAME ="/getflightbookingdetailsbyusername/{username}";
	public static final String UPDATE_FLIGHT_BY_FLIGHT_ID ="/update/{id}";
	public static final String DELETE_FLIGHT_BY_FLIGHT_ID ="/delete/{id}";
	public static final String CANCEL_PAYMENT_BY_FLIGHT_BOOKING_ID="/resetstatus/{id}";
	public static final String GET_ALL_CITY_NAMES="/getallcitynames";
	public static final String PAYMENT_STATUS_CHANGE_BY_FLIGHT_BOOKING_ID="/paymentstatuschange/{bookingid}";
	public static final String GET_ALL_AVAILABLE_FLIGHT="/getallavailableflights/{from}/{to}/{departure}/{travellerClass}";

}
