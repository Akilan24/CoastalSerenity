package com.userservice.constant;

public class UserConstant {

	public static final String USER = "/User";

	public static final String GET_ALL_USERS = "/getallusers";
	public static final String UPDATE_USER_BY_USERNAME = "/updateuser/{username}";
	public static final String GET_USER_BY_USERNAME = "/getuserbyname/{username}";
	public static final String GET_USER_BY_EMAIL = "/getuserbyemail/{email}";
	public static final String GET_USER_BY_MOBILE = "/getuserbymobile/{mobile}";
	public static final String DELETE_USER_BY_USERNAME = "/deleteuserbyusername/{username}";
	public static final String UPDATE_USER_PASSWORD_BY_USERNAME = "/updatepassword/{username}/{password}";

	public static final String GET_ALL_TRAVELLERS = "/getalltravellers/{username}";
	public static final String ADD_TRAVELLER_BY_USERNAME = "/addtraveller/{username}";
	public static final String UPDATE_TRAVELLER_BY_USERNAME = "/updatetraveller/{username}";
	public static final String GET_TRAVELLER_BY_NAME = "/gettraveller/{username}/{name}";
	public static final String DELETE_TRAVELLER_BY_NAME = "/deletetraveller/{username}/{name}";

}
