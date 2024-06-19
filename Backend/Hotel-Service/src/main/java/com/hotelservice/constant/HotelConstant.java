package com.hotelservice.constant;

public class HotelConstant {

	public static final String HOTEL = "/Hotel";

	public static final String GET_ALL_HOTEL ="/getallhotel";
	public static final String GET_ALL_HOTEL_NAMES_BY_CITY = "/getallhotelnamesbycity/{city}";
	public static final String GET_ALL_HOTEL_CITY_NAMES = "/getallhotelcitynames";
	public static final String GET_HOTEL_BY_HOTEL_ID= "/gethotelbyid/{id}";
	public static final String GET_HOTEL_BY_HOTEL_NAME="/gethotelbyhotelname/{hotelname}";
	public static final String GET_ALL_HOTEL_BY_CITY_NAME= "/gethotelbycityname/{cityname}";
	public static final String ADD_HOTEL= "/addhotel";
	public static final String UPDATE_HOTEL= "/updatehotel";
	public static final String DELETE_HOTEL_BY_HOTEL_ID= "/deletebyid/{id}";
	public static final String GET_ALL_HOTEL_BOOKING_DETAILS= "/HotelBookingDetails/getall";
	public static final String GET_ALL_AVAILABLE_ROOMS= "/HotelBookingDetails/availablerooms/{city}/{roomtype}/{fromDate}/{ToDate}";
	public static final String GET_HOTEL_BOOKING_DETAILS_BY_ID= "/HotelBookingDetails/getbyid/{bookingid}";
	public static final String GET_HOTEL_BOOKING_DETAILS_BY_USERNAME_AND_HOTELNAME= "/HotelBookingDetails/getbyusernameandhotelname/{username}/{hotelName}";
	public static final String GET_HOTEL_BOOKING_DETAILS_BY_USERNAME= "/HotelBookingDetails/getbookingdetailsbyusername/{username}";
	public static final String PAYMENT_STATUS_CHANGE_BY_HOTEL_BOOKING_ID="/HotelBookingDetails/paymentstatuschangebybid/{bookingid}";
	public static final String BOOK_ROOM_BY_USERNAME= "/HotelBookingDetails/bookroom/{username}";
	public static final String DELETE_HOTEL_BOOKING_DETAILS_BY_HOTEL_BOOKING_ID= "/HotelBookingDetails/deletebyid/{bookingid}";
	public static final String ADD_GUEST_BY_BOOKING_ID= "/HotelBookingDetails/addguests/{bookingid}";
	public static final String CANCEL_PAYMENT_BY_HOTEL_BOOKING_ID= "/HotelBookingDetails/resetstatus/{id}";

}
