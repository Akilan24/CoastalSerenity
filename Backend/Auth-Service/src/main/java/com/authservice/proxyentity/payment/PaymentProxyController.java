package com.authservice.proxyentity.payment;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "PAYMENT-SERVICE", url = "http://localhost:8084/Payment")
public interface PaymentProxyController {

	@GetMapping("/doPayment/{bookingid}")
	public ResponseEntity<Payment> addPayment(@PathVariable String bookingid) throws Exception;

	@GetMapping("/getallpayment")
	public ResponseEntity<List<Payment>> getallpayments();

	@GetMapping("/getpaymentbybookingid/{bookingid}")
	public ResponseEntity<Payment> getpaymentbybookingid(@PathVariable long bookingid);

	@GetMapping("/getpaymentbypaymentid/{paymentid}")
	public ResponseEntity<Payment> getpaymentbypaymentid(@PathVariable long paymentid);

	@GetMapping("/paymentCancel/{paymentid}")
	public ResponseEntity<String> paymentCancel(@PathVariable long paymentid);

}
