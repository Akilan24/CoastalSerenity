package com.flightservice;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import com.flightservice.entity.Flight;
import com.flightservice.entity.FlightBookingDetails;
import com.flightservice.entity.FlightSeats;
import com.flightservice.entity.FlightTravellerFlightSeats;
import com.flightservice.exception.FlightDetailsNotFoundException;
import com.flightservice.externalclass.Registration;
import com.flightservice.externalclass.Traveller;
import com.flightservice.proxy.UserProxy;
import com.flightservice.repository.FlightBookingDetailsRepository;
import com.flightservice.repository.FlightRepository;
import com.flightservice.repository.FlightSeatsRepository;
import com.flightservice.service.FlightServiceImpl;

@ExtendWith(MockitoExtension.class)
public class FlightServiceTest {

	@InjectMocks
	private FlightServiceImpl flightService;

	@Mock
	private FlightRepository flightRepository;

	@Mock
	private FlightSeatsRepository flightSeatsRepository;

	@Mock
	private FlightBookingDetailsRepository flightBookingDetailsRepository;

	@Mock
	private UserProxy userProxy;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	public void testGetAllFlight() {
		List<Flight> flights = Arrays.asList(new Flight(), new Flight());
		when(flightRepository.findAll()).thenReturn(flights);

		List<Flight> result = flightService.getAllFlight();
		assertEquals(2, result.size());
		verify(flightRepository, times(1)).findAll();
	}

	@Test
	public void testGetAllFlight_Empty() {
		when(flightRepository.findAll()).thenReturn(Collections.emptyList());

		assertThrows(FlightDetailsNotFoundException.class, () -> flightService.getAllFlight());
		verify(flightRepository, times(1)).findAll();
	}

	@Test
	public void testGetFlightByFlightId() {
		Flight flight = new Flight();
		when(flightRepository.findById(100001L)).thenReturn(Optional.of(flight));

		Optional<Flight> result = flightService.getFlightByFlightId(100001L);
		assertTrue(result.isPresent());
		assertEquals(flight, result.get());
		verify(flightRepository, times(1)).findById(100001L);
	}

	@Test
	public void testGetFlightByFlightId_NotFound() {
		when(flightRepository.findById(100001L)).thenReturn(Optional.empty());

		assertThrows(FlightDetailsNotFoundException.class, () -> flightService.getFlightByFlightId(100001L));
		verify(flightRepository, times(1)).findById(100001L);
	}

	@Test
	public void testSaveFlight() {
		Flight flight = new Flight();
		flight.setDuration("2:30");
		flight.setDepartureTime(LocalDateTime.now());
		when(flightRepository.findAll()).thenReturn(Collections.emptyList());
		when(flightRepository.save(flight)).thenReturn(flight);

		Flight result = flightService.saveFlight(flight);

		assertEquals(100000, result.getFlightId());
		assertNotNull(result.getArrivalTime());
		verify(flightRepository, times(1)).save(flight);
	}

	@Test
	public void testDeleteFlight() {
		Flight flight = new Flight();
		when(flightRepository.findById(100001L)).thenReturn(Optional.of(flight));

		String result = flightService.deleteFlightByFlightId(100001L);

		assertEquals("Flight details are deleted", result);
		verify(flightRepository, times(1)).deleteById(100001L);
	}

	@Test
	public void testDeleteFlight_NotFound() {
		when(flightRepository.findById(100001L)).thenReturn(Optional.empty());

		assertThrows(FlightDetailsNotFoundException.class, () -> flightService.deleteFlightByFlightId(100001L));
		verify(flightRepository, times(1)).findById(100001L);
	}

	@Test
	public void testUpdateFlight() {
		Flight existingFlight = new Flight();
		existingFlight.setFlightId(100001L);
		Flight updatedFlight = new Flight();
		updatedFlight.setDuration("3:45");
		updatedFlight.setDepartureTime(LocalDateTime.now());

		when(flightRepository.findById(100001L)).thenReturn(Optional.of(existingFlight));
		when(flightRepository.save(existingFlight)).thenReturn(existingFlight);

		Flight result = flightService.updateFlightByFlightId(100001L, updatedFlight);

		assertEquals(updatedFlight.getDuration(), result.getDuration());
		verify(flightRepository, times(1)).save(existingFlight);
	}

	@Test
	public void testUpdateFlight_NotFound() {
		Flight updatedFlight = new Flight();
		updatedFlight.setDuration("3:45");
		updatedFlight.setDepartureTime(LocalDateTime.now());

		when(flightRepository.findById(100001L)).thenReturn(Optional.empty());

		assertThrows(FlightDetailsNotFoundException.class,
				() -> flightService.updateFlightByFlightId(100001L, updatedFlight));
	}

	@Test
	public void testCancelPaymentByTrainBookingId() {
		FlightBookingDetails bookingDetails = new FlightBookingDetails();
		when(flightBookingDetailsRepository.findById(100001L)).thenReturn(Optional.of(bookingDetails));

		FlightBookingDetails result = flightService.cancelPaymentByFlightBookingId(100001L);

		assertEquals("Payment Cancelled & Refunded", result.getPaymentStatus());
		verify(flightBookingDetailsRepository, times(1)).save(bookingDetails);
	}

	@Test
	public void testCancelPaymentByTrainBookingId_NotFound() {
		when(flightBookingDetailsRepository.findById(100001L)).thenReturn(Optional.empty());

		assertThrows(FlightDetailsNotFoundException.class, () -> flightService.cancelPaymentByFlightBookingId(100001L));
	}

	@Test
	public void testGetAllCityNames() {
		Flight flight1 = new Flight();
		flight1.setOrigin("CityA");
		flight1.setDestination("CityB");

		Flight flight2 = new Flight();
		flight2.setOrigin("CityC");
		flight2.setDestination("CityD");

		List<Flight> flights = Arrays.asList(flight1, flight2);
		when(flightRepository.findAll()).thenReturn(flights);

		List<List<String>> result = flightService.getAllCityNames();

		assertEquals(2, result.size());
		assertTrue(result.get(0).contains("CityA"));
		assertTrue(result.get(1).contains("CityD"));
	}

	@Test
	public void testGetAllAvailableFlights() {
		Flight flight1 = new Flight();
		flight1.setOrigin("CityA");
		flight1.setDestination("CityB");
		flight1.setDepartureTime(LocalDateTime.now());
		Map<String, Integer> bookingStatus = new HashMap<>();
		bookingStatus.put("Economy", 5);
		flight1.setFlightBookingStatus(bookingStatus);

		Flight flight2 = new Flight();
		flight2.setOrigin("CityA");
		flight2.setDestination("CityB");
		flight2.setDepartureTime(LocalDateTime.now().plusDays(1));
		flight2.setFlightBookingStatus(bookingStatus);

		List<Flight> flights = Arrays.asList(flight1, flight2);
		when(flightRepository.findAll()).thenReturn(flights);

		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date departureDate = new Date();
		String departureString = formatter.format(departureDate);

		List<Flight> result = flightService.getAllAvailableFlights("CityA", "CityB", departureDate, "Economy");

		assertEquals(1, result.size());
		assertEquals(flight1, result.get(0));
	}

	@Test
	public void testBookFlight() {
		Flight flight = new Flight();
		flight.setFlightId(100001L);
		flight.setOrigin("CityA");
		flight.setDestination("CityB");
		flight.setDepartureTime(LocalDateTime.now());
		flight.setArrivalTime(LocalDateTime.now().plusHours(2));
		flight.setAirline("Airline1");
		flight.setFlightModel("Model1");
		flight.setAirlineLogo("Logo1");

		FlightSeats seat = new FlightSeats(1, "A1", "Economy", 100.0, true);
		Traveller traveller = new Traveller();
		traveller.setName("Traveller1");

		FlightTravellerFlightSeats ftfs = new FlightTravellerFlightSeats();
		ftfs.setTravellers(Collections.singletonList(traveller));
		ftfs.setFlightSeats(Collections.singletonList(seat));

		Registration user = new Registration();
		user.setName("User1");
		user.setEmail("user1@example.com");
		user.setMobile("1234567890");

		when(flightRepository.findById(100001L)).thenReturn(Optional.of(flight));
		when(userProxy.showUserByUserName("user1")).thenReturn(user);
		when(flightSeatsRepository.findById(1L)).thenReturn(Optional.of(seat));

		FlightBookingDetails result = flightService.bookFlight(100001L, ftfs, "user1");

		assertNotNull(result);
		assertEquals("CityA", result.getOrigin());
		verify(flightRepository, times(1)).save(flight);
	}

	@Test
	public void testAddSeatsByFlightId() {
		Flight flight = new Flight();
		flight.setFlightId(100001L);
		Map<String, Double> seatPriceMap = new HashMap<>();
		seatPriceMap.put("firstclass", 200.0);
		seatPriceMap.put("business", 150.0);
		seatPriceMap.put("economy", 100.0);
		flight.setSeatPrice(seatPriceMap);

		when(flightRepository.findById(100001L)).thenReturn(Optional.of(flight));
		when(flightRepository.save(flight)).thenReturn(flight);

		Flight result = flightService.addSeatsByFlightId(100001L);

		assertNotNull(result);
		assertEquals(100001L, result.getFlightId());
		assertFalse(result.getFlightSeats().isEmpty());
		verify(flightRepository, times(1)).save(flight);
	}

	@Test
	public void testAddSeatsByFlightId_NotFound() {
		when(flightRepository.findById(100001L)).thenReturn(Optional.empty());

		assertThrows(FlightDetailsNotFoundException.class, () -> flightService.addSeatsByFlightId(100001L));
	}

	@Test
	public void testPaymentStatusChangeByTrainBookingId() {
		FlightBookingDetails bookingDetails = new FlightBookingDetails();
		bookingDetails.setFlightBookingId(100001L);
		when(flightBookingDetailsRepository.findById(100001L)).thenReturn(Optional.of(bookingDetails));

		FlightBookingDetails result = flightService.paymentStatusChangeByFlightBookingId(100001L);

		assertEquals("Payment done", result.getPaymentStatus());
		verify(flightBookingDetailsRepository, times(1)).save(bookingDetails);
	}

	@Test
	public void testPaymentStatusChangeByTrainBookingId_NotFound() {
		when(flightBookingDetailsRepository.findById(100001L)).thenReturn(Optional.empty());

		assertThrows(FlightDetailsNotFoundException.class,
				() -> flightService.paymentStatusChangeByFlightBookingId(100001L));
	}

	@Test
	public void testGetFlightBookingDetailsByFlightBookingId() {
		FlightBookingDetails bookingDetails = new FlightBookingDetails();
		bookingDetails.setFlightBookingId(100001L);
		when(flightBookingDetailsRepository.findById(100001L)).thenReturn(Optional.of(bookingDetails));

		FlightBookingDetails result = flightService.getFlightBookingDetailsByFlightBookingId(100001L);

		assertNotNull(result);
		assertEquals(100001L, result.getFlightBookingId());
	}

	@Test
	public void testGetFlightBookingDetailsByFlightBookingId_NotFound() {
		when(flightBookingDetailsRepository.findById(100001L)).thenReturn(Optional.empty());

		assertThrows(FlightDetailsNotFoundException.class,
				() -> flightService.getFlightBookingDetailsByFlightBookingId(100001L));
	}

	@Test
	public void testGetFlightBookingDetailsByUsername() {
		FlightBookingDetails bookingDetails1 = new FlightBookingDetails();
		bookingDetails1.setUsername("user1");
		bookingDetails1.setBookedDate(LocalDateTime.now().minusDays(1));

		FlightBookingDetails bookingDetails2 = new FlightBookingDetails();
		bookingDetails2.setUsername("user1");
		bookingDetails2.setBookedDate(LocalDateTime.now());

		List<FlightBookingDetails> bookingDetailsList = Arrays.asList(bookingDetails1, bookingDetails2);
		when(flightBookingDetailsRepository.findAll()).thenReturn(bookingDetailsList);

		List<FlightBookingDetails> result = flightService.getFlightBookingDetailsByUsername("user1");

		assertEquals(2, result.size());
		assertEquals(bookingDetails2, result.get(0));
	}

	@Test
	public void testGetFlightBookingDetailsByUsername_NotFound() {
		when(flightBookingDetailsRepository.findAll()).thenReturn(Collections.emptyList());

		assertThrows(FlightDetailsNotFoundException.class,
				() -> flightService.getFlightBookingDetailsByUsername("user1"));
	}
}
