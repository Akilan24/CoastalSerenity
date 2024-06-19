package com.cabservice.constant;

public class CabConstant {

	public static final String CAB = "/Cab";
	
	public static final String GET_ALL_CAB ="/getall";
	public static final String GET_CAB_BY_CAB_ID ="/getbyid/{id}";
	public static final String ADD_SEATS_BY_CAB_ID ="/addseats/{id}";
	public static final String SAVE_CAB ="/save";
	public static final String BOOK_CAB_BY_CAB_ID ="/bookCab/{id}/{username}";
	public static final String BOOK_RENTAL_CAB_BY_CAB_ID ="/bookRentalCab/{id}/{username}";
	public static final String GET_CAB_BOOKING_DETAILS_BY_CAB_BOOKING_ID ="/getCabbookingdetailsbyid/{id}";
	public static final String GET_CAB_BOOKING_DETAILS_BY_USERNAME ="/getCabbookingdetailsbyusername/{username}";
	public static final String GET_RENTAL_CAB_BOOKING_DETAILS_BY_RENTAL_CAB_BOOKING_ID ="/getRentalCabbookingdetailsbyid/{id}";
	public static final String GET_RENTAL_CAB_BOOKING_DETAILS_BY_USERNAME ="/getRentalCabbookingdetailsbyusername/{username}";
	public static final String UPDATE_CAB_BY_CAB_ID ="/update/{id}";
	public static final String DELETE_CAB_BY_CAB_ID ="/delete/{id}";
	public static final String CANCEL_PAYMENT_BY_CAB_BOOKING_ID="/resetstatusCab/{id}";
	public static final String CANCEL_PAYMENT_BY_RENTAL_CAB_BOOKING_ID="/resetstatusRentalCab/{id}";
	public static final String GET_ALL_CITY_NAMES="/getallcitynames";
	public static final String PAYMENT_STATUS_CHANGE_BY_CAB_BOOKING_ID="/paymentstatuschangeCab/{bookingid}";
	public static final String PAYMENT_STATUS_CHANGE_BY_RENTAL_CAB_BOOKING_ID="/paymentstatuschangeRentalCab/{bookingid}";
	public static final String GET_CAB_DETAILS_AND_TRIP_DETAILS="/getRentalCabAndRentalPackageDetails";
	public static final String GET_RENTAL_CAB_AND_RENTAL_PACKAGE_DETAILS="/getRentalCabAndRentalPackageDetails";
	public static final String SAVE_TRIP="/saveTrip";
	public static final String UPDATE_TRIP_BY_TRIP_ID="/updateTrip/{tripId}";
	public static final String DELETE_TRIP_BY_TRIP_ID="/deleteTrip/{tripId}";
	public static final String GET_ALL_TRIP="/getAllTrip";
	public static final String GET_TRIP_BY_TRIP_ID="/getTripbyId/{tripId}";
	public static final String SAVE_RENTAL_CAB="/saveRentalCab";
	public static final String UPDATE_RENTAL_CAB_BY_RENTAL_CAB_ID="/updateRentalCab/{rentalCabId}";
	public static final String DELETE_RENTAL_CAB_BY_RENTAL_CAB_ID="/deleteRentalCab/{rentalCabId}";
	public static final String GET_ALL_RENTAL_CAB="/getAllRentalCab";
	public static final String GET_RENTAL_CAB_BY_RENTAL_CAB_ID="/getRentalCabbyId/{rentalCabId}";
	public static final String SAVE_RENTAL_PACKAGE="/saveRentalPackage";
	public static final String GET_ALL_RENTAL_CITY_NAMES="/getallRentalcitynames";
	public static final String UPDATE_RENTAL_PACKAGE_BY_RENTAL_PACKAGE_ID="/updateRentalPackage/{rentalPackageId}";
	public static final String DELETE_RENTAL_PACKAGE_BY_RENTAL_PACKAGE_ID="/deleteRentalPackage/{rentalPackageId}";
	public static final String GET_ALL_RENTAL_PACKAGE="/getAllRentalPackage";
	public static final String GET_RENTAL_PACKAGE_BY_RENTAL_PACKAGE_ID="/getRentalPackagebyId/{rentalPackageId}";

}
