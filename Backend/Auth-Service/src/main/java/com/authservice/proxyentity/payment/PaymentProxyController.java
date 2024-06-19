package com.authservice.proxyentity.payment;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.authservice.constant.PaymentConstant;

@FeignClient(name = PaymentConstant.SERVICE, url = PaymentConstant.URL)
public interface PaymentProxyController {

	@GetMapping(PaymentConstant.DO_PAYMENT_BY_BOOKING_ID)
	public ResponseEntity<Payment> doPaymentByBookingId(@PathVariable String bookingid) throws Exception;

	@GetMapping(PaymentConstant.GET_ALL_PAYMENT)
	public ResponseEntity<List<Payment>> getAllPayment();

	@GetMapping(PaymentConstant.GET_PAYMENT_BY_BOOKING_ID)
	public ResponseEntity<Payment> getPaymentByBookingId(@PathVariable long bookingid);

	@GetMapping(PaymentConstant.GET_PAYMENT_BY_PAYMENT_ID)
	public ResponseEntity<Payment> getPaymentByPaymentId(@PathVariable long paymentid);

	@GetMapping(PaymentConstant.CANCEL_PAYMENT_BY_PAYMENT_ID)
	public ResponseEntity<String> cancelPaymentByPaymentId(@PathVariable String paymentid);

}
