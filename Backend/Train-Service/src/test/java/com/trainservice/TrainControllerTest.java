package com.trainservice;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.trainservice.controller.TrainController;
import com.trainservice.entity.Train;
import com.trainservice.entity.TrainBookingDetails;
import com.trainservice.externalclass.Traveller;
import com.trainservice.service.TrainService;

@ExtendWith(MockitoExtension.class)
class TrainControllerTest {

    @InjectMocks
    private TrainController trainController;

    @Mock
    private TrainService trainService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllTrain() {
        List<Train> trains = Arrays.asList(new Train(), new Train());
        when(trainService.getAllTrain()).thenReturn(trains);

        ResponseEntity<List<Train>> response = trainController.getAllTrain();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(2, Objects.requireNonNull(response.getBody()).size());
        verify(trainService, times(1)).getAllTrain();
    }

    @Test
    void testGetTrainByTrainId() {
        long trainId = 1;
        Optional<Train> train = Optional.of(new Train());
        when(trainService.getTrainByTrainId(trainId)).thenReturn(train);

        ResponseEntity<Optional<Train>> response = trainController.getTrainByTrainId(trainId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(response.getBody().isPresent());
        verify(trainService, times(1)).getTrainByTrainId(trainId);
    }

    @Test
    void testAddSeatsByTrainId() {
        long trainId = 1;
        Train train = new Train();
        when(trainService.addSeatsByTrainId(trainId)).thenReturn(train);

        ResponseEntity<Train> response = trainController.addSeatsByTrainId(trainId);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
        verify(trainService, times(1)).addSeatsByTrainId(trainId);
    }

    @Test
    void testSaveTrain() {
        Train train = new Train();
        when(trainService.saveTrain(train)).thenReturn(train);

        ResponseEntity<Train> response = trainController.saveTrain(train);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
        verify(trainService, times(1)).saveTrain(train);
    }

    @Test
    void testBookTrain() {
        long trainId = 1;
        List<Traveller> travellers = Arrays.asList(new Traveller(), new Traveller());
        String seatType = "SL";
        String boardingStation = "StationA";
        String username = "user123";
        TrainBookingDetails bookingDetails = new TrainBookingDetails();
        when(trainService.bookTrain(trainId, travellers, seatType, boardingStation, username)).thenReturn(bookingDetails);

        ResponseEntity<TrainBookingDetails> response = trainController.bookTrain(trainId, travellers, seatType, boardingStation, username);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
        verify(trainService, times(1)).bookTrain(trainId, travellers, seatType, boardingStation, username);
    }

    @Test
    void testGetTrainBookingDetailsById() {
        long id = 1;
        TrainBookingDetails bookingDetails = new TrainBookingDetails();
        when(trainService.getTrainBookingDetailsByTrainBookingId(id)).thenReturn(bookingDetails);

        ResponseEntity<TrainBookingDetails> response = trainController.getTrainBookingDetailsByTrainBookingId(id);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        verify(trainService, times(1)).getTrainBookingDetailsByTrainBookingId(id);
    }

    @Test
    void testGetTrainBookingDetailsByUserName() {
        String username = "user123";
        List<TrainBookingDetails> bookingDetailsList = Arrays.asList(new TrainBookingDetails(), new TrainBookingDetails());
        when(trainService.getTrainBookingDetailsByUserName(username)).thenReturn(bookingDetailsList);

        ResponseEntity<List<TrainBookingDetails>> response = trainController.getTrainBookingDetailsByUserName(username);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(2, Objects.requireNonNull(response.getBody()).size());
        verify(trainService, times(1)).getTrainBookingDetailsByUserName(username);
    }

    @Test
    void testUpdateTrainByTrainId() {
        long trainId = 1;
        Train train = new Train();
        when(trainService.updateTrainByTrainId(trainId, train)).thenReturn(train);

        ResponseEntity<Train> response = trainController.updateTrainByTrainId(trainId, train);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        verify(trainService, times(1)).updateTrainByTrainId(trainId, train);
    }

    @Test
    void testDeleteTrainByTrainId() {
        long trainId = 1;
        when(trainService.deleteTrainByTrainId(trainId)).thenReturn("Train deleted");

        ResponseEntity<String> response = trainController.deleteTrainByTrainId(trainId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Train deleted", response.getBody());
        verify(trainService, times(1)).deleteTrainByTrainId(trainId);
    }

    @Test
    void testCancelPaymentByTrainBookingId() {
        long trainId = 1;
        TrainBookingDetails bookingDetails = new TrainBookingDetails();
        when(trainService.cancelPaymentByTrainBookingId(trainId)).thenReturn(bookingDetails);

        ResponseEntity<TrainBookingDetails> response = trainController.cancelPaymentByTrainBookingId(trainId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        verify(trainService, times(1)).cancelPaymentByTrainBookingId(trainId);
    }

    @Test
    void testGetAllCityNames() {
        List<List<String>> cityNames = Arrays.asList(Arrays.asList("CityA", "CityB"), Arrays.asList("CityC", "CityD"));
        when(trainService.getAllCityNames()).thenReturn(cityNames);

        ResponseEntity<List<List<String>>> response = trainController.getAllCityNames();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(2, Objects.requireNonNull(response.getBody()).size());
        verify(trainService, times(1)).getAllCityNames();
    }

    @Test
    void testPaymentStatusChangeByTrainBookingId() {
        long bookingId = 1;
        TrainBookingDetails bookingDetails = new TrainBookingDetails();
        when(trainService.paymentStatusChangeByTrainBookingId(bookingId)).thenReturn(bookingDetails);

        ResponseEntity<TrainBookingDetails> response = trainController.paymentStatusChangeByTrainBookingId(bookingId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        verify(trainService, times(1)).paymentStatusChangeByTrainBookingId(bookingId);
    }

    @Test
    void testGetAllAvailableTrains() {
        List<Train> trains = Arrays.asList(new Train(), new Train());
        String from = "CityA";
        String to = "CityB";
        Date departure = java.sql.Date.valueOf("2024-06-19");

        when(trainService.getAllAvailableTrains(from, to, departure)).thenReturn(trains);

        ResponseEntity<List<Train>> response = trainController.getAllAvailableTrains(from, to, departure);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(2, Objects.requireNonNull(response.getBody()).size());
        verify(trainService, times(1)).getAllAvailableTrains(from, to, departure);
    }
}
