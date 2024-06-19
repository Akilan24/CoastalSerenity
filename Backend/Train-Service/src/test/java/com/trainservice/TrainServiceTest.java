package com.trainservice;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
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
import org.springframework.http.ResponseEntity;

import com.trainservice.entity.Train;
import com.trainservice.entity.TrainBookingDetails;
import com.trainservice.exception.TrainDetailsNotFoundException;
import com.trainservice.externalclass.Registration;
import com.trainservice.externalclass.Traveller;
import com.trainservice.proxy.UserProxy;
import com.trainservice.repository.TrainBookingDetailsRepository;
import com.trainservice.repository.TrainRepository;
import com.trainservice.service.TrainServiceImpl;

@ExtendWith(MockitoExtension.class)
class TrainServiceTest {

    @InjectMocks
    private TrainServiceImpl trainService;

    @Mock
    private TrainRepository trainRepository;

    @Mock
    private TrainBookingDetailsRepository trainBookingDetailsRepository;

    @Mock
    private UserProxy userProxy;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllTrain() {
        List<Train> trains = Arrays.asList(new Train(), new Train());
        when(trainRepository.findAll()).thenReturn(trains);

        List<Train> result = trainService.getAllTrain();

        assertEquals(2, result.size());
        verify(trainRepository, times(1)).findAll();
    }

    @Test
    void testGetAllTrain_Empty() {
        when(trainRepository.findAll()).thenReturn(Collections.emptyList());

        assertThrows(TrainDetailsNotFoundException.class, () -> trainService.getAllTrain());
    }

    @Test
    void testGetTrainByTrainId() {
        Train train = new Train();
        when(trainRepository.findById(1L)).thenReturn(Optional.of(train));

        Optional<Train> result = trainService.getTrainByTrainId(1L);

        assertTrue(result.isPresent());
        verify(trainRepository, times(1)).findById(1L);
    }

    @Test
    void testGetTrainByTrainId_NotFound() {
        when(trainRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(TrainDetailsNotFoundException.class, () -> trainService.getTrainByTrainId(1L));
    }

    @Test
    void testSaveTrain() {
        Train train = new Train();
        train.setDuration("02:30");
        train.setDepartureTime(LocalDateTime.of(2023, 6, 19, 12, 0));
        when(trainRepository.findAll()).thenReturn(Collections.emptyList());
        when(trainRepository.save(any(Train.class))).thenReturn(train);

        Train result = trainService.saveTrain(train);

        assertEquals(train, result);
        assertEquals(LocalDateTime.of(2023, 6, 19, 14, 30), train.getArrivalTime());
        verify(trainRepository, times(1)).save(train);
    }

    @Test
    void testDeleteTrainByTrainId() {
        Train train = new Train();
        when(trainRepository.findById(1L)).thenReturn(Optional.of(train));

        String result = trainService.deleteTrainByTrainId(1L);

        assertEquals("Train details are deleted", result);
        verify(trainRepository, times(1)).deleteById(1L);
    }

    @Test
    void testDeleteTrainByTrainId_NotFound() {
        when(trainRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(TrainDetailsNotFoundException.class, () -> trainService.deleteTrainByTrainId(1L));
    }

    @Test
    void testUpdateTrainByTrainId() {
        Train existingTrain = new Train();
        existingTrain.setTrainId(1L);
        Train updatedTrain = new Train();
        updatedTrain.setDuration("02:30");
        updatedTrain.setDepartureTime(LocalDateTime.of(2023, 6, 19, 12, 0));

        when(trainRepository.findById(1L)).thenReturn(Optional.of(existingTrain));
        when(trainRepository.save(any(Train.class))).thenReturn(existingTrain);

        Train result = trainService.updateTrainByTrainId(1L, updatedTrain);

        assertEquals(existingTrain, result);
        assertEquals(LocalDateTime.of(2023, 6, 19, 14, 30), existingTrain.getArrivalTime());
        verify(trainRepository, times(1)).save(existingTrain);
    }

    @Test
    void testUpdateTrainByTrainId_NotFound() {
        when(trainRepository.findById(1L)).thenReturn(Optional.empty());

        Train train = new Train();
        train.setTrainId(1L);

        assertThrows(TrainDetailsNotFoundException.class, () -> trainService.updateTrainByTrainId(1L, train));
    }

    @Test
    void testCancelPaymentByTrainBookingId() {
        TrainBookingDetails bookingDetails = new TrainBookingDetails();
        when(trainBookingDetailsRepository.findById(1L)).thenReturn(Optional.of(bookingDetails));
        when(trainBookingDetailsRepository.save(any(TrainBookingDetails.class))).thenReturn(bookingDetails);

        TrainBookingDetails result = trainService.cancelPaymentByTrainBookingId(1L);

        assertEquals("Payment Cancelled & Refunded", result.getPaymentStatus());
        verify(trainBookingDetailsRepository, times(1)).save(bookingDetails);
    }

    @Test
    void testCancelPaymentByTrainBookingId_NotFound() {
        when(trainBookingDetailsRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(TrainDetailsNotFoundException.class, () -> trainService.cancelPaymentByTrainBookingId(1L));
    }

    @Test
    void testGetAllCityNames() {
        Train train1 = new Train();
        train1.setOrigin("CityA");
        train1.setDestination("CityB");
        Train train2 = new Train();
        train2.setOrigin("CityC");
        train2.setDestination("CityD");

        when(trainRepository.findAll()).thenReturn(Arrays.asList(train1, train2));

        List<List<String>> result = trainService.getAllCityNames();

        assertEquals(2, result.get(0).size());
        assertEquals(2, result.get(1).size());
        verify(trainRepository, times(1)).findAll();
    }

    @Test
    void testGetAllCityNames_Empty() {
        when(trainRepository.findAll()).thenReturn(Collections.emptyList());

        assertThrows(TrainDetailsNotFoundException.class, () -> trainService.getAllCityNames());
    }

    @Test
    void testGetAllAvailableTrains() {
        Train train = new Train();
        train.setOrigin("CityA");
        train.setDestination("CityB");
        train.setDepartureTime(LocalDateTime.of(2023, 6, 19, 12, 0));
        Date departure = java.sql.Date.valueOf("2023-06-19");

        when(trainRepository.findAll()).thenReturn(Arrays.asList(train));

        List<Train> result = trainService.getAllAvailableTrains("CityA", "CityB", departure);

        assertEquals(1, result.size());
        verify(trainRepository, times(1)).findAll();
    }

    @Test
    void testGetAllAvailableTrains_Empty() {
        when(trainRepository.findAll()).thenReturn(Collections.emptyList());

        Date departure = java.sql.Date.valueOf("2023-06-19");
        assertThrows(TrainDetailsNotFoundException.class, () -> trainService.getAllAvailableTrains("CityA", "CityB", departure));
    }

    @Test
    void testBookTrain() {
        Train train = new Train();
        train.setTrainBookingStatus(Map.of("SL", 10));
        train.setSeatPrice(Map.of("SL", 500.0));
        when(trainRepository.findById(1L)).thenReturn(Optional.of(train));

        Traveller traveller = new Traveller();
        traveller.setName("John Doe");
        traveller.setAge(30);
        List<Traveller> travellers = Arrays.asList(traveller);

        Registration registration = new Registration();
        registration.setName("John Doe");
        registration.setEmail("john.doe@example.com");
        registration.setMobile("1234567890");
        when(userProxy.showUserByUserName("john")).thenReturn(registration);

        TrainBookingDetails bookingDetails = new TrainBookingDetails();
        bookingDetails.setTrainPassenger(new ArrayList<>());
        when(trainBookingDetailsRepository.save(any(TrainBookingDetails.class))).thenReturn(bookingDetails);

        TrainBookingDetails result = trainService.bookTrain(1L, travellers, "SL", "CityA", "john");

        assertEquals("Payment has to be done", result.getPaymentStatus());
        verify(trainBookingDetailsRepository, times(1)).save(any(TrainBookingDetails.class));
    }

    @Test
    void testBookTrain_NotFound() {
        when(trainRepository.findById(1L)).thenReturn(Optional.empty());

        Traveller traveller = new Traveller();
        List<Traveller> travellers = Arrays.asList(traveller);

        assertThrows(TrainDetailsNotFoundException.class, () -> trainService.bookTrain(1L, travellers, "SL", "CityA", "john"));
    }

    @Test
    void testAddSeatsByTrainId() {
        Train train = new Train();
        train.setSeatPrice(Map.of("1A", 1000.0, "2A", 800.0, "3A", 600.0, "SL", 300.0, "CC", 500.0));
        when(trainRepository.findById(1L)).thenReturn(Optional.of(train));

        Train updatedTrain = new Train();
        updatedTrain.setTrainSeats(null);

        when(trainRepository.save(any(Train.class))).thenReturn(updatedTrain);

        Train result = trainService.addSeatsByTrainId(1L);

        assertEquals(updatedTrain, result);
        verify(trainRepository, times(1)).save(any(Train.class));
    }

    @Test
    void testAddSeatsByTrainId_NotFound() {
        when(trainRepository.findById(1L)).thenReturn(Optional.empty());

        Train train = new Train();
        train.setTrainSeats(null);

        assertThrows(TrainDetailsNotFoundException.class, () -> trainService.addSeatsByTrainId(1L));
    }
}
