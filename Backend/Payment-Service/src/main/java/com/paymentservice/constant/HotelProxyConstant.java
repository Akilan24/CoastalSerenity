package com.paymentservice.constant;

public class HotelProxyConstant {

	public static final String SERVICE= "HOTEL-SERVICE";
	public static final String URL = "http://localhost:8082/Hotel";

	public static final String GET_HOTEL_BOOKING_DETAILS_BY_HOTEL_BOOKING_ID = "/HotelBookingDetails/getbyid/{bookingid}";
	public static final String PAYMENT_STATUS_CHANGE_BY_HOTEL_BOOKING_ID = "/HotelBookingDetails/paymentstatuschangebybid/{bookingid}";
	public static final String CANCEL_PAYMENT_BY_HOTEL_BOOKING_ID= "/HotelBookingDetails/resetstatus/{id}";

}
