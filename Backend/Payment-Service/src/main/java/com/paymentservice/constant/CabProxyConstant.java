package com.paymentservice.constant;

public class CabProxyConstant {

	public static final String SERVICE = "CAB-SERVICE";
	public static final String URL = "http://localhost:8085/Cab";

	public static final String GET_CAB_BOOKING_DETAILS_BY_CAB_BOOKING_ID = "/getCabbookingdetailsbyid/{id}";
	public static final String PAYMENT_STATUS_CHANGE_BY_CAB_BOOKING_ID = "/paymentstatuschangeCab/{bookingid}";
	public static final String CANCEL_PAYMENT_BY_CAB_BOOKING_ID = "/resetstatusCab/{id}";

	public static final String GET_RENTAL_CAB_BOOKING_DETAILS_BY_RENTAL_CAB_BOOKING_ID = "/getRentalCabbookingdetailsbyid/{id}";
	public static final String PAYMENT_STATUS_CHANGE_BY_RENTAL_CAB_BOOKING_ID = "/paymentstatuschangRentalCab/{bookingid}";
	public static final String CANCEL_PAYMENT_BY_RENTAL_CAB_BOOKING_ID = "/resetstatusRentalCab/{id}";

}
