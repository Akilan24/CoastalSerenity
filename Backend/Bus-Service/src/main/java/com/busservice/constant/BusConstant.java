package com.busservice.constant;

public class BusConstant {

	public static final String BUS = "/Bus";
	
	public static final String GET_ALL_BUS ="/getall";
	public static final String GET_BUS_BY_BUS_ID ="/getbyid/{busId}";
	public static final String ADD_SEATS_BY_BUS_ID ="/addseats/{busId}";
	public static final String SAVE_BUS ="/save";
	public static final String BOOK_BUS_BY_BUS_ID ="/bookBus/{id}/{username}/{pickUpPoint}/{dropPoint}";
	public static final String GET_BUS_BOOKING_DETAILS_BY_BUS_BOOKING_ID ="/getbusbookingdetailsbyid/{id}";
	public static final String GET_BUS_BOOKING_DETAILS_BY_USERNAME ="/getBusbookingdetailsbyusername/{username}";
	public static final String UPDATE_BUS_BY_BUS_ID ="/update/{busId}";
	public static final String DELETE_BUS_BY_BUS_ID ="/delete/{busId}";
	public static final String CANCEL_PAYMENT_BY_BUS_BOOKING_ID="/resetstatus/{busId}";
	public static final String GET_ALL_CITY_NAMES="/getallcitynames";
	public static final String PAYMENT_STATUS_CHANGE_BY_BUS_BOOKING_ID="/paymentstatuschange/{bookingid}";
	public static final String GET_ALL_AVAILABLE_BUS="/getallavailablebuses/{from}/{to}/{departure}";

}
