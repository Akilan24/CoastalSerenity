package com.paymentservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.paymentservice.entity.Payment;
import com.paymentservice.service.PaymentService;

@RestController
@RequestMapping("/Payment")
public class PaymentController {

	@Autowired
	private PaymentService paymentService;

	@PostMapping("/doPayment/{bookingid}")
	public ResponseEntity<Payment> addPayment(@PathVariable int bookingid) throws Exception {

		return new ResponseEntity<>(paymentService.doPayment(bookingid), HttpStatus.OK);
	}

	@GetMapping("/getallpayment")
	public ResponseEntity<List<Payment>> getallpayments() {
		return new ResponseEntity<>(paymentService.getallpayment(), HttpStatus.OK);
	}

	@GetMapping("/getpaymentbybookingid/{bookingid}")
	public ResponseEntity<Payment> getpaymentbybookingid(@PathVariable int bookingid) {
		return new ResponseEntity<>(paymentService.getPaymentbyBookingId(bookingid), HttpStatus.OK);
	}

	@GetMapping("/getpaymentbypaymentid/{paymentid}")
	public ResponseEntity<Payment> getpaymentbypaymentid(@PathVariable long paymentid) {
		return new ResponseEntity<>(paymentService.getPaymentbyPaymentId(paymentid), HttpStatus.OK);
	}

	@GetMapping("/paymentCancel/{paymentid}")
	public ResponseEntity<String> paymentCancel(@PathVariable long paymentid) {
		return new ResponseEntity<>(paymentService.paymentCancel(paymentid), HttpStatus.OK);
	}

}
