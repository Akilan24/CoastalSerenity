package com.paymentservice.constant;

public class FlightProxyConstant {

	public static final String SERVICE = "FLIGHT-SERVICE";
	public static final String URL = "http://localhost:8086/Flight";

	public static final String GET_FLIGHT_BOOKING_DETAILS_BY_FLIGHT_BOOKING_ID = "/getflightbookingdetailsbyid/{id}";
	public static final String PAYMENT_STATUS_CHANGE_BY_FLIGHT_BOOKING_ID = "/paymentstatuschange/{bookingid}";
	public static final String CANCEL_PAYMENT_BY_FLIGHT_BOOKING_ID = "/resetstatus/{id}";

}
