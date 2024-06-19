package com.paymentservice.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.paymentservice.entity.Payment;

@Service
public interface PaymentService {

	Payment doPaymentByBookingId(String bookingid);

	Payment getPaymentbyBookingId(long bookingid);

	List<Payment> getAllPayment();

	String cancelPaymentByPaymentId(String id);

	Payment getPaymentbyPaymentId(long id);

}
