package com.paymentservice.constant;

public class PaymentConstant {

	public static final String PAYMENT = "/Payment";

	public static final String DO_PAYMENT_BY_BOOKING_ID = "/getallpayment";
	public static final String GET_ALL_PAYMENT = "/doPayment/{bookingid}";
	public static final String GET_PAYMENT_BY_BOOKING_ID = "/getpaymentbybookingid/{bookingid}";
	public static final String GET_PAYMENT_BY_PAYMENT_ID = "/getpaymentbypaymentid/{paymentid}";
	public static final String CANCEL_PAYMENT_BY_PAYMENT_ID= "/paymentCancel/{paymentid}";

}
