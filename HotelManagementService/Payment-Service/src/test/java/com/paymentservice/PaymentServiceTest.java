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
import com.paymentservice.externalclass.BookingDetails;
import com.paymentservice.proxy.BookingDetailsProxy;
import com.paymentservice.repository.PaymentRepository;
import com.paymentservice.service.PaymentServiceImpl;

@ExtendWith(MockitoExtension.class)
public class PaymentServiceTest {

	@Mock
	PaymentRepository paymentRepository;

	@Mock
	BookingDetailsProxy bdproxy;

	@InjectMocks
	PaymentServiceImpl paymentService;

	@Test
	void testDoPayment() {
		int bookingid = 123456;
		BookingDetails bookingDetails = new BookingDetails();
		bookingDetails.setUsername("user123");
		bookingDetails.setAmount(100.0);
		when(bdproxy.getBookingDetails(bookingid)).thenReturn(bookingDetails);

		Payment payment = new Payment();
		payment.setPayment_id(100001);
		payment.setBookingid(bookingid);
		payment.setPaymentDate(new Date());
		payment.setUsername("user123");
		payment.setAmount(5000.0);
		payment.setPaymentStatus("Payment Done");
		when(paymentRepository.save(any())).thenReturn(payment);

		Payment result = paymentService.doPayment(bookingid);

		assertNotNull(result);
		assertEquals(100001, result.getPayment_id());
		assertEquals(bookingid, result.getBookingid());
		assertEquals("user123", result.getUsername());
		assertEquals(5000.0, result.getAmount());
		assertEquals("Payment Done", result.getPaymentStatus());
	}

	@Test
	void testGetPaymentbyBookingId() {
		int bookingid = 123456;
		Payment payment = new Payment();
		payment.setPayment_id(100001);
		payment.setBookingid(bookingid);
		payment.setPaymentDate(new Date());
		payment.setUsername("user123");
		payment.setAmount(5000.0);
		payment.setPaymentStatus("Payment Done");
		when(paymentRepository.findByBookingid(bookingid)).thenReturn(Optional.of(payment));

		Payment result = paymentService.getPaymentbyBookingId(bookingid);

		assertNotNull(result);
		assertEquals(100001, result.getPayment_id());
		assertEquals(bookingid, result.getBookingid());
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
		payment1.setPayment_id(100001);
		payment1.setBookingid(123456);
		payment1.setPaymentDate(new Date());
		payment1.setUsername("user123");
		payment1.setAmount(5000.0);
		payment1.setPaymentStatus("Payment Done");
		paymentList.add(payment1);

		when(paymentRepository.findAll()).thenReturn(paymentList);

		List<Payment> result = paymentService.getallpayment();

		assertNotNull(result);
		assertEquals(1, result.size());
		assertEquals(100001, result.get(0).getPayment_id());
		assertEquals(123456, result.get(0).getBookingid());
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
		long id = 100001;
		int bookingid = 123456;
		Payment payment = new Payment();
		payment.setPayment_id(id);
		payment.setBookingid(bookingid);
		payment.setPaymentDate(new Date());
		payment.setUsername("user123");
		payment.setAmount(5000.0);
		payment.setPaymentStatus("Payment Done");
		when(paymentRepository.findById(id)).thenReturn(Optional.of(payment));
		when(bdproxy.getBookingDetails(bookingid)).thenReturn(new BookingDetails());

		String result = paymentService.paymentCancel(id);

		assertNotNull(result);
		assertEquals("Payment cancelled and refunded", result);
		assertEquals("Payment cancelled and refunded", payment.getPaymentStatus());
	}

	@Test
	void testPaymentCancel_NotFound() {
		long id = 100001;
		when(paymentRepository.findById(id)).thenReturn(Optional.empty());

		assertThrows(PaymentDetailsNotFoundException.class, () -> {
			paymentService.paymentCancel(id);
		});
	}

	@Test
	void testGetPaymentbyPaymentId() {
		long id = 100001;
		Payment payment = new Payment();
		payment.setPayment_id(id);
		payment.setBookingid(123456);
		payment.setPaymentDate(new Date());
		payment.setUsername("user123");
		payment.setAmount(5000.0);
		payment.setPaymentStatus("Payment Done");
		when(paymentRepository.findById(id)).thenReturn(Optional.of(payment));

		Payment result = paymentService.getPaymentbyPaymentId(id);

		assertNotNull(result);
		assertEquals(id, result.getPayment_id());
		assertEquals(123456, result.getBookingid());
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
