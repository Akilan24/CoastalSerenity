package com.paymentservice.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.paymentservice.entity.Payment;

@Service
public interface PaymentService {

	Payment doPayment(String bookingid);

	Payment getPaymentbyBookingId(long bookingid);

	List<Payment> getallpayment();

	String paymentCancel(String id);

	Payment getPaymentbyPaymentId(long id);

}
