package com.authservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.authservice.ProxyEntity.Payment;
import com.authservice.proxyController.PaymentProxyController;

@RestController
@RequestMapping("/HMA/Payment")
@CrossOrigin("http://localhost:5173")
public class PaymentController {

	@Autowired
	private PaymentProxyController paymentProxy;

	@PostMapping("/doPayment/{bookingid}")
	public ResponseEntity<Payment> addPayment(@PathVariable int bookingid) throws Exception {

		return paymentProxy.addPayment(bookingid);
	}

	@GetMapping("/getallpayment")
	public ResponseEntity<List<Payment>> getallpayments() {
		return paymentProxy.getallpayments();
	}

	@GetMapping("/getpaymentbybookingid/{bookingid}")
	public ResponseEntity<Payment> getpaymentbybookingid(@PathVariable int bookingid) {
		return paymentProxy.getpaymentbybookingid(bookingid);
	}

	@GetMapping("/getpaymentbypaymentid/{paymentid}")
	public ResponseEntity<Payment> getpaymentbypaymentid(@PathVariable long paymentid) {
		return paymentProxy.getpaymentbypaymentid(paymentid);
	}

	@GetMapping("/paymentCancel/{paymentid}")
	public ResponseEntity<String> paymentCancel(@PathVariable long paymentid) {
		return paymentProxy.paymentCancel(paymentid);
	}

}
