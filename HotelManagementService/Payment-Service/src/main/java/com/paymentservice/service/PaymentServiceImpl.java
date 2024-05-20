package com.paymentservice.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.paymentservice.entity.Payment;
import com.paymentservice.exception.PaymentDetailsNotFoundException;
import com.paymentservice.externalclass.BookingDetails;
import com.paymentservice.proxy.BookingDetailsProxy;
import com.paymentservice.repository.PaymentRepository;

@Service
public class PaymentServiceImpl implements PaymentService {
	@Autowired
	private PaymentRepository paymentRepository;

	@Autowired
	BookingDetailsProxy bdproxy;

	@Override
	public Payment doPayment(int bookingid) {
		BookingDetails bd = bdproxy.getBookingDetails(bookingid);

		Payment p = new Payment();

		int MIN_ID = 100000;
		int count = paymentRepository.findAll().size();
		if (count == 0)
			p.setPayment_id(count);
		else
			p.setPayment_id(MIN_ID + 1);

		p.setBookingid(bookingid);
		p.setPaymentDate(DateNow());
		p.setUsername(bd.getUsername());
		p.setAmount(bd.getAmount());
		p.setPaymentStatus("Payment Done");
		bdproxy.paymentstatuschange(bookingid);
		return paymentRepository.save(p);
	}

	@Override
	public Payment getPaymentbyBookingId(int bookingid) {
		if (paymentRepository.findByBookingid(bookingid).isPresent())
			return paymentRepository.findByBookingid(bookingid).get();
		else
			throw new PaymentDetailsNotFoundException("Payment not found");

	}

	public Date DateNow() {
		LocalDate localDate = LocalDate.now();
		Date date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		String fd = formatter.format(date);
		try {
			date = formatter.parse(fd);

		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

	@Override
	public List<Payment> getallpayment() {
		if (!paymentRepository.findAll().isEmpty())
			return paymentRepository.findAll();
		else
			throw new PaymentDetailsNotFoundException("Payment not found");
	}

	@Override
	public String paymentCancel(long id) {

		Payment p;
		if (paymentRepository.findById(id).isPresent()) {
			p = paymentRepository.findById(id).get();
			p.setPaymentStatus("Payment cancelled and refunded");
			paymentRepository.save(p);
			bdproxy.getBookingDetails(p.getBookingid()).setPaymentStatus("Payment cancelled");
			return "Payment cancelled and refunded";
		} else
			throw new PaymentDetailsNotFoundException("Payment not found");

	}

	@Override
	public Payment getPaymentbyPaymentId(long id) {
		if (paymentRepository.findById(id).isPresent())
			return paymentRepository.findById(id).get();
		else
			throw new PaymentDetailsNotFoundException("Payment not found");

	}

}
