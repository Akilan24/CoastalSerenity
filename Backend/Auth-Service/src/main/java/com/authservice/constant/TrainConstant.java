package com.authservice.constant;

public class TrainConstant {

	public static final String TRAIN = "/CS/Train";
	public static final String CROSS_ORIGIN = "http://localhost:5173";
	public static final String SERVICE = "TRAIN-SERVICE";
	public static final String URL = "http://localhost:8088/Train";
	public static final String GET_ALL_TRAIN="/getAllTrain";
	public static final String GET_TRAIN_BY_TRAIN_ID="/getbyid/{TrainId}";
	public static final String UPDATE_TRAIN_BY_TRAIN_ID="/update/{TrainId}";
	public static final String DELETE_TRAIN_BY_TRAIN_ID="/delete/{TrainId}";
	public static final String CANCEL_PAYMENT_BY_TRAIN_BOOKING_ID="/resetstatus/{TrainId}";
	public static final String ADD_SEATS_BY_TRAIN_ID="/addseats/{TrainId}";	
	public static final String GET_ALL_CITY_NAMES="/getallcitynames";	
	public static final String PAYMENT_STATUS_CHANGE_BY_TRAIN_BOOKING_ID="/paymentstatuschange/{bookingid}";
	public static final String SAVE_TRAIN="/save";	
	public static final String GET_ALL_AVAILABLE_TRAINS="/getallavailableTrains/{from}/{to}/{departure}";	
	public static final String BOOK_TRAIN_BY_TRAIN_ID="/bookTrain/{id}/{seatType}/{boardingStation}/{username}";	
	public static final String GET_TRAIN_BOOKING_DETAILS_BY_ID="/getTrainbookingdetailsbyid/{id}";	
	public static final String GET_TRAIN_BOOKING_DETAILS_BY_USERNAME="/getTrainbookingdetailsbyusername/{username}";	
 
}
