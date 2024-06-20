package com.authservice.constant;

public class PaymentConstant {

	public static final String PAYMENT = "/CS/Payment";
	public static final String CROSS_ORIGIN = "http://localhost:5173";
	public static final String SERVICE = "PAYMENT-SERVICE";
	public static final String URL = "http://localhost:8084/Payment";
	public static final String DO_PAYMENT_BY_BOOKING_ID = "/doPayment/{bookingid}";
	public static final String GET_ALL_PAYMENT = "/getallpayment";
	public static final String GET_PAYMENT_BY_BOOKING_ID = "/getpaymentbybookingid/{bookingid}";
	public static final String GET_PAYMENT_BY_PAYMENT_ID = "/getpaymentbypaymentid/{paymentid}";
	public static final String CANCEL_PAYMENT_BY_PAYMENT_ID = "/paymentCancel/{paymentid}";
}
