package com.paymentservice.constant;

public class BusProxyConstant {

	public static final String SERVICE = "BUS-SERVICE";
	public static final String URL = "http://localhost:8087/Bus";

	public static final String GET_BUS_BOOKING_DETAILS_BY_BUS_BOOKING_ID = "/getbusbookingdetailsbyid/{id}";
	public static final String PAYMENT_STATUS_CHANGE_BY_BUS_BOOKING_ID = "/paymentstatuschange/{bookingid}";
	public static final String CANCEL_PAYMENT_BY_BUS_BOOKING_ID= "/resetstatus/{busId}";

}
