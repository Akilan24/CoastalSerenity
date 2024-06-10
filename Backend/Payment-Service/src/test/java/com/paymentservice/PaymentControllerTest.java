package com.paymentservice;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.paymentservice.controller.PaymentController;
import com.paymentservice.entity.Payment;
import com.paymentservice.service.PaymentService;

@ExtendWith(MockitoExtension.class)
class PaymentControllerTest {

	@Mock
	private PaymentService paymentService;

	@InjectMocks
	private PaymentController paymentController;

	String value = "String-123456";
	String[] id=value.split("-");
	
	@Test
	void testAddPayment() throws Exception {
		Payment payment = new Payment(123456, 987654, new Date(), "user123", 5000.0, "Payment Done");
		when(paymentService.doPayment(id[1])).thenReturn(payment);

		ResponseEntity<Payment> response = paymentController.addPayment(id[1]);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(payment, response.getBody());
	}

	@Test
	void testGetAllPayments() {
		List<Payment> payments = new ArrayList<>();
		when(paymentService.getallpayment()).thenReturn(payments);

		ResponseEntity<List<Payment>> response = paymentController.getallpayments();

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(payments, response.getBody());
	}

	@Test
	void testGetPaymentByBookingId() {
		int bookingId = 987654;
		Payment payment = new Payment(123456, 987654, new Date(), "user123", 5000.0, "Payment Done");
		when(paymentService.getPaymentbyBookingId(bookingId)).thenReturn(payment);

		ResponseEntity<Payment> response = paymentController.getpaymentbybookingid(bookingId);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(payment, response.getBody());
	}

	@Test
	void testGetPaymentByPaymentId() {
		long paymentId = 123456;
		Payment payment = new Payment(123456, 987654, new Date(), "user123", 5000.0, "Payment Done");
		when(paymentService.getPaymentbyPaymentId(paymentId)).thenReturn(payment);

		ResponseEntity<Payment> response = paymentController.getpaymentbypaymentid(paymentId);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(payment, response.getBody());
	}

	@Test
	void testPaymentCancel() {
		String cancellationMessage = "Payment cancelled successfully";
		when(paymentService.paymentCancel(id[1])).thenReturn(cancellationMessage);

		ResponseEntity<String> response = paymentController.paymentCancel(id[1]);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(cancellationMessage, response.getBody());
	}
}
