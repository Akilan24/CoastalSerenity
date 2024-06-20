package com.authservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.authservice.constant.PaymentConstant;
import com.authservice.proxyentity.payment.Payment;
import com.authservice.proxyentity.payment.PaymentProxyController;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping(PaymentConstant.PAYMENT)
@CrossOrigin(PaymentConstant.CROSS_ORIGIN)
public class PaymentController {

	@Autowired
	private PaymentProxyController paymentProxy;

	@GetMapping(PaymentConstant.DO_PAYMENT_BY_BOOKING_ID)
	public ResponseEntity<Payment> doPaymentByBookingId(@PathVariable String bookingid) throws Exception {
		log.info("doPaymentByBookingId controller called");
		return paymentProxy.doPaymentByBookingId(bookingid);
	}

	@GetMapping(PaymentConstant.GET_ALL_PAYMENT)
	public ResponseEntity<List<Payment>> getAllPayment() {
		log.info("getAllPayment controller called");
		return paymentProxy.getAllPayment();
	}

	@GetMapping(PaymentConstant.GET_PAYMENT_BY_BOOKING_ID)
	public ResponseEntity<Payment> getPaymentByBookingId(@PathVariable long bookingid) {
		log.info("getPaymentByBookingId controller called");
		return paymentProxy.getPaymentByBookingId(bookingid);
	}

	@GetMapping(PaymentConstant.GET_PAYMENT_BY_PAYMENT_ID)
	public ResponseEntity<Payment> getPaymentByPaymentId(@PathVariable long paymentid) {
		log.info("getPaymentByPaymentId controller called");
		return paymentProxy.getPaymentByPaymentId(paymentid);
	}

	@GetMapping(PaymentConstant.CANCEL_PAYMENT_BY_PAYMENT_ID)
	public ResponseEntity<String> cancelPaymentByPaymentId(@PathVariable String paymentid) {
		log.info("cancelPaymentByPaymentId controller called");
		return paymentProxy.cancelPaymentByPaymentId(paymentid);
	}

}
