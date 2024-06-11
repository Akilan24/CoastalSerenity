package com.paymentservice.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.paymentservice.entity.Payment;
import com.paymentservice.exception.PaymentDetailsNotFoundException;
import com.paymentservice.externalclass.BusBookingDetails;
import com.paymentservice.externalclass.FlightBookingDetails;
import com.paymentservice.externalclass.HotelBookingDetails;
import com.paymentservice.proxy.BusBookingDetailsProxy;
import com.paymentservice.proxy.FlightBookingDetailsProxy;
import com.paymentservice.proxy.HotelBookingDetailsProxy;
import com.paymentservice.repository.PaymentRepository;

@Service
public class PaymentServiceImpl implements PaymentService {
	@Autowired
	private PaymentRepository paymentRepository;

	@Autowired
	HotelBookingDetailsProxy hbdproxy;

	@Autowired
	FlightBookingDetailsProxy fbdproxy;

	@Autowired
	BusBookingDetailsProxy bbdproxy;
	
	@Override
	public Payment doPayment(String bookingid) {
		String[] value = bookingid.split("-");
		long id = Long.parseLong(value[1]);

		Payment p = new Payment();

		long MIN_ID = 100000;
		int count = paymentRepository.findAll().size();
		p.setPaymentid(count == 0 ? MIN_ID : MIN_ID + count);
		p.setBookingId(id);
		p.setPaymentDate(LocalDateTime.now());
		if (value[0].equalsIgnoreCase("hotel")) {
			HotelBookingDetails hbd = hbdproxy.getBookingDetails(id);
			p.setUsername(hbd.getName());
			p.setAmount(hbd.getAmount());
			p.setPaymentStatus("Payment Done");
			hbdproxy.paymentstatuschange(id);
		} else if (value[0].equalsIgnoreCase("flight")) {
			FlightBookingDetails fbd = fbdproxy.getFlightBookingDetailsById(id);
			p.setUsername(fbd.getName());
			p.setAmount(fbd.getTotalPrice());
			p.setPaymentStatus("Payment Done");
			fbdproxy.paymentstatuschange(id);
		}else if (value[0].equalsIgnoreCase("bus")) {
			BusBookingDetails bbd = bbdproxy.getBusBookingDetailsById(id);
			p.setUsername(bbd.getName());
			p.setAmount(bbd.getTotalPrice());
			p.setPaymentStatus("Payment Done");
			fbdproxy.paymentstatuschange(id);
		}

		return paymentRepository.save(p);
	}

	@Override
	public Payment getPaymentbyBookingId(long bookingid) {
		if (paymentRepository.findByBookingid(bookingid).isPresent())
			return paymentRepository.findByBookingid(bookingid).get();
		else
			throw new PaymentDetailsNotFoundException("Payment not found");

	}

	public Date DateNow() {
		LocalDate localDate = LocalDate.now();
		Date date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
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
	public String paymentCancel(String id) {
		String[] value =id.split("-");
		long paymentid = Long.parseLong(value[1]);
		Payment p;
		if (paymentRepository.findById(paymentid).isPresent()) {
			p = paymentRepository.findById(paymentid).get();
			p.setPaymentStatus("Payment cancelled and refunded");
			paymentRepository.save(p);
			if(value[1].equalsIgnoreCase("hotel"))
			    hbdproxy.getBookingDetails(p.getBookingId()).setPaymentStatus("Payment cancelled");
			else if(value[1].equalsIgnoreCase("flight"))
			    fbdproxy.getFlightBookingDetailsById(p.getBookingId()).setPaymentStatus("Payment cancelled");
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
