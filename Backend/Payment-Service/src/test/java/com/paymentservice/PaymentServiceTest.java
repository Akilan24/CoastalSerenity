package com.paymentservice;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.paymentservice.entity.Payment;
import com.paymentservice.exception.PaymentDetailsNotFoundException;
import com.paymentservice.externalclass.HotelBookingDetails;
import com.paymentservice.proxy.HotelBookingDetailsProxy;
import com.paymentservice.repository.PaymentRepository;
import com.paymentservice.service.PaymentServiceImpl;

@ExtendWith(MockitoExtension.class)
public class PaymentServiceTest {

	@Mock
	PaymentRepository paymentRepository;

	@Mock
	HotelBookingDetailsProxy hbdproxy;

	@InjectMocks
	PaymentServiceImpl paymentService;

	String value = "String-123456";
	String[] id=value.split("-");
	
	@Test
	void testDoPayment() {
		
		HotelBookingDetails bookingDetails = new HotelBookingDetails();
		bookingDetails.setName("user123");
		bookingDetails.setAmount(100.0);
		when(hbdproxy.getBookingDetails(Long.parseLong(id[1]))).thenReturn(bookingDetails);

		Payment payment = new Payment();
		payment.setPaymentid(100001);
		payment.setBookingId(Long.parseLong(id[1]));
		payment.setPaymentDate(new Date());
		payment.setUsername("user123");
		payment.setAmount(5000.0);
		payment.setPaymentStatus("Payment Done");
		when(paymentRepository.save(any())).thenReturn(payment);

		Payment result = paymentService.doPayment(value);

		assertNotNull(result);
		assertEquals(100001, result.getPaymentid());
		assertEquals(Long.parseLong(id[1]), result.getBookingId());
		assertEquals("user123", result.getUsername());
		assertEquals(5000.0, result.getAmount());
		assertEquals("Payment Done", result.getPaymentStatus());
	}

	@Test
	void testGetPaymentbyBookingId() {
		int bookingid = 123456;
		Payment payment = new Payment();
		payment.setPaymentid(100001);
		payment.setBookingId(bookingid);
		payment.setPaymentDate(new Date());
		payment.setUsername("user123");
		payment.setAmount(5000.0);
		payment.setPaymentStatus("Payment Done");
		when(paymentRepository.findByBookingid(bookingid)).thenReturn(Optional.of(payment));

		Payment result = paymentService.getPaymentbyBookingId(bookingid);

		assertNotNull(result);
		assertEquals(100001, result.getPaymentid());
		assertEquals(bookingid, result.getBookingId());
		assertEquals("user123", result.getUsername());
		assertEquals(5000.0, result.getAmount());
		assertEquals("Payment Done", result.getPaymentStatus());
	}

	@Test
	void testGetPaymentbyBookingId_NotFound() {
		int bookingid = 123456;
		when(paymentRepository.findByBookingid(bookingid)).thenReturn(Optional.empty());

		assertThrows(PaymentDetailsNotFoundException.class, () -> {
			paymentService.getPaymentbyBookingId(bookingid);
		});
	}

	@Test
	void testGetallpayment() {
		List<Payment> paymentList = new ArrayList<>();
		Payment payment1 = new Payment();
		payment1.setPaymentid(100001);
		payment1.setBookingId(123456);
		payment1.setPaymentDate(new Date());
		payment1.setUsername("user123");
		payment1.setAmount(5000.0);
		payment1.setPaymentStatus("Payment Done");
		paymentList.add(payment1);

		when(paymentRepository.findAll()).thenReturn(paymentList);

		List<Payment> result = paymentService.getallpayment();

		assertNotNull(result);
		assertEquals(1, result.size());
		assertEquals(100001, result.get(0).getPaymentid());
		assertEquals(123456, result.get(0).getBookingId());
		assertEquals("user123", result.get(0).getUsername());
		assertEquals(5000.0, result.get(0).getAmount());
		assertEquals("Payment Done", result.get(0).getPaymentStatus());
	}

	@Test
	void testGetallpayment_NotFound() {
		when(paymentRepository.findAll()).thenReturn(new ArrayList<>());

		assertThrows(PaymentDetailsNotFoundException.class, () -> {
			paymentService.getallpayment();
		});
	}

	@Test
	void testPaymentCancel() {
		
		int bookingid = 123456;
		Payment payment = new Payment();
		payment.setPaymentid(Long.parseLong(id[1]));
		payment.setBookingId(bookingid);
		payment.setPaymentDate(new Date());
		payment.setUsername("user123");
		payment.setAmount(5000.0);
		payment.setPaymentStatus("Payment Done");
		when(paymentRepository.findById(Long.parseLong(id[1]))).thenReturn(Optional.of(payment));
		when(hbdproxy.getBookingDetails(bookingid)).thenReturn(new HotelBookingDetails());

		String result = paymentService.paymentCancel(id[1]);

		assertNotNull(result);
		assertEquals("Payment cancelled and refunded", result);
		assertEquals("Payment cancelled and refunded", payment.getPaymentStatus());
	}

	@Test
	void testPaymentCancel_NotFound() {
		
		when(paymentRepository.findById(Long.parseLong(id[1]))).thenReturn(Optional.empty());

		assertThrows(PaymentDetailsNotFoundException.class, () -> {
			paymentService.paymentCancel(id[1]);
		});
	}

	@Test
	void testGetPaymentbyPaymentId() {
		long id = 100001;
		Payment payment = new Payment();
		payment.setPaymentid(id);
		payment.setBookingId(123456);
		payment.setPaymentDate(new Date());
		payment.setUsername("user123");
		payment.setAmount(5000.0);
		payment.setPaymentStatus("Payment Done");
		when(paymentRepository.findById(id)).thenReturn(Optional.of(payment));

		Payment result = paymentService.getPaymentbyPaymentId(id);

		assertNotNull(result);
		assertEquals(id, result.getPaymentid());
		assertEquals(123456, result.getBookingId());
		assertEquals("user123", result.getUsername());
		assertEquals(5000.0, result.getAmount());
		assertEquals("Payment Done", result.getPaymentStatus());
	}

	@Test
	void testGetPaymentbyPaymentId_NotFound() {
		long id = 100001;
		when(paymentRepository.findById(id)).thenReturn(Optional.empty());

		assertThrows(PaymentDetailsNotFoundException.class, () -> {
			paymentService.getPaymentbyPaymentId(id);
		});
	}

}
