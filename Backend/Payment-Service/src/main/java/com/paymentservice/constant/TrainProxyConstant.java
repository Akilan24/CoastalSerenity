package com.paymentservice.constant;

public class TrainProxyConstant {

	public static final String SERVICE = "TRAIN-SERVICE";
	public static final String URL = "http://localhost:8088/Train";

	public static final String GET_TRAIN_BOOKING_DETAILS_BY_TRAIN_BOOKING_ID = "/getTrainbookingdetailsbyid/{id}";
	public static final String PAYMENT_STATUS_CHANGE_BY_TRAIN_BOOKING_ID = "/paymentstatuschange/{bookingid}";
	public static final String CANCEL_PAYMENT_BY_TRAIN_BOOKING_ID = "/resetstatus/{TrainId}";

}
