package com.flightservice;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.flightservice.constant.FlightConstant;
import com.flightservice.controller.FlightController;
import com.flightservice.entity.Flight;
import com.flightservice.entity.FlightBookingDetails;
import com.flightservice.entity.FlightTravellerFlightSeats;
import com.flightservice.service.FlightService;

@ExtendWith(MockitoExtension.class)
public class FlightControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private FlightService flightService;

	@InjectMocks
	private FlightController flightController;

	private ObjectMapper objectMapper = new ObjectMapper();

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	public void testGetAllFlight() throws Exception {
		List<Flight> flights = Arrays.asList(new Flight(), new Flight());
		when(flightService.getAllFlight()).thenReturn(flights);

		mockMvc.perform(get(FlightConstant.FLIGHT + FlightConstant.GET_ALL_FLIGHT)).andExpect(status().isOk())
				.andExpect(jsonPath("$.length()").value(2));
	}

	@Test
	public void testGetFlightByFlightId() throws Exception {
		Flight flight = new Flight();
		when(flightService.getFlightByFlightId(100001L)).thenReturn(Optional.of(flight));

		mockMvc.perform(get(FlightConstant.FLIGHT + FlightConstant.GET_FLIGHT_BY_FLIGHT_ID, 100001L))
				.andExpect(status().isOk()).andExpect(jsonPath("$.flightId").value(100001L));
	}

	@Test
	public void testAddSeatsByFlightId() throws Exception {
		Flight flight = new Flight();
		when(flightService.addSeatsByFlightId(100001L)).thenReturn(flight);

		mockMvc.perform(put(FlightConstant.FLIGHT + FlightConstant.ADD_SEATS_BY_FLIGHT_ID, 100001L))
				.andExpect(status().isOk()).andExpect(jsonPath("$.flightId").value(100001L));
	}

	@Test
	public void testCreateFlight() throws Exception {
		Flight flight = new Flight();
		flight.setFlightId(100001L);
		when(flightService.saveFlight(any(Flight.class))).thenReturn(flight);

		mockMvc.perform(post(FlightConstant.FLIGHT + FlightConstant.SAVE_FLIGHT).contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(flight))).andExpect(status().isCreated())
				.andExpect(jsonPath("$.flightId").value(100001L));
	}

	@Test
	public void testBookFlight() throws Exception {
		FlightBookingDetails bookingDetails = new FlightBookingDetails();
		when(flightService.bookFlight(anyLong(), any(FlightTravellerFlightSeats.class), anyString()))
				.thenReturn(bookingDetails);

		FlightTravellerFlightSeats ftfs = new FlightTravellerFlightSeats();

		mockMvc.perform(post(FlightConstant.FLIGHT + FlightConstant.BOOK_FLIGHT_BY_FLIGHT_ID, 100001L, "user1")
				.contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(ftfs)))
				.andExpect(status().isCreated()).andExpect(jsonPath("$.flightBookingId").exists());
	}

	@Test
	public void testGetFlightBookingDetailsByFlightBookingId() throws Exception {
		FlightBookingDetails bookingDetails = new FlightBookingDetails();
		bookingDetails.setFlightBookingId(100001L);
		when(flightService.getFlightBookingDetailsByFlightBookingId(100001L)).thenReturn(bookingDetails);

		mockMvc.perform(
				get(FlightConstant.FLIGHT + FlightConstant.GET_FLIGHT_BOOKING_DETAILS_BY_FLIGHT_BOOKING_ID, 100001L))
				.andExpect(status().isOk()).andExpect(jsonPath("$.flightBookingId").value(100001L));
	}

	@Test
	public void testGetFlightBookingDetailsByUsername() throws Exception {
		FlightBookingDetails bookingDetails1 = new FlightBookingDetails();
		FlightBookingDetails bookingDetails2 = new FlightBookingDetails();
		List<FlightBookingDetails> bookingDetailsList = Arrays.asList(bookingDetails1, bookingDetails2);
		when(flightService.getFlightBookingDetailsByUsername("user1")).thenReturn(bookingDetailsList);

		mockMvc.perform(get(FlightConstant.FLIGHT + FlightConstant.GET_FLIGHT_BOOKING_DETAILS_BY_USERNAME, "user1"))
				.andExpect(status().isOk()).andExpect(jsonPath("$.length()").value(2));
	}

	@Test
	public void testUpdateFlight() throws Exception {
		Flight flight = new Flight();
		flight.setFlightId(100001L);
		when(flightService.updateFlightByFlightId(eq(100001L), any(Flight.class))).thenReturn(flight);

		mockMvc.perform(put(FlightConstant.FLIGHT + FlightConstant.UPDATE_FLIGHT_BY_FLIGHT_ID, 100001L)
				.contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(flight)))
				.andExpect(status().isOk()).andExpect(jsonPath("$.flightId").value(100001L));
	}

	@Test
	public void testDeleteFlight() throws Exception {
		when(flightService.deleteFlightByFlightId(100001L)).thenReturn("Flight details are deleted");

		mockMvc.perform(delete(FlightConstant.FLIGHT + FlightConstant.DELETE_FLIGHT_BY_FLIGHT_ID, 100001L))
				.andExpect(status().isOk()).andExpect(content().string("Flight details are deleted"));
	}

	@Test
	public void testCancelPaymentByTrainBookingId() throws Exception {
		FlightBookingDetails bookingDetails = new FlightBookingDetails();
		when(flightService.cancelPaymentByFlightBookingId(100001L)).thenReturn(bookingDetails);

		mockMvc.perform(put(FlightConstant.FLIGHT + FlightConstant.CANCEL_PAYMENT_BY_FLIGHT_BOOKING_ID, 100001L))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.paymentStatus").value("Payment Cancelled & Refunded"));
	}

	@Test
	public void testGetAllCityNames() throws Exception {
		List<List<String>> cityNames = Arrays.asList(Arrays.asList("CityA", "CityB"), Arrays.asList("CityC", "CityD"));
		when(flightService.getAllCityNames()).thenReturn(cityNames);

		mockMvc.perform(get(FlightConstant.FLIGHT + FlightConstant.GET_ALL_CITY_NAMES)).andExpect(status().isOk())
				.andExpect(jsonPath("$.length()").value(2));
	}

	@Test
	public void testPaymentStatusChangeByTrainBookingId() throws Exception {
		FlightBookingDetails bookingDetails = new FlightBookingDetails();
		bookingDetails.setPaymentStatus("Payment done");
		when(flightService.paymentStatusChangeByFlightBookingId(100001L)).thenReturn(bookingDetails);

		mockMvc.perform(get(FlightConstant.FLIGHT + FlightConstant.PAYMENT_STATUS_CHANGE_BY_FLIGHT_BOOKING_ID, 100001L))
				.andExpect(status().isOk()).andExpect(jsonPath("$.paymentStatus").value("Payment done"));
	}

	@Test
	public void testGetAllAvailableFlights() throws Exception {
		List<Flight> flights = Arrays.asList(new Flight(), new Flight());
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date departureDate = formatter.parse("2023-01-01");

		when(flightService.getAllAvailableFlights(anyString(), anyString(), any(Date.class), anyString()))
				.thenReturn(flights);

		mockMvc.perform(get(FlightConstant.FLIGHT + FlightConstant.GET_ALL_AVAILABLE_FLIGHT, "CityA", "CityB",
				"2023-01-01", "Economy")).andExpect(status().isOk()).andExpect(jsonPath("$.length()").value(2));
	}
}
