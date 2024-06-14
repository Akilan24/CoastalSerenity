//package com.authservice.controller;
//
//import java.time.LocalDateTime;
//import java.util.Date;
//import java.util.List;
//import java.util.Optional;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.format.annotation.DateTimeFormat;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.PutMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.authservice.proxyentity.cab.Cab;
//import com.authservice.proxyentity.cab.CabProxyController;
//import com.cabservice.entity.CabBookingDetails;
//
//@RestController
//@RequestMapping("/CS/Cab")
//@CrossOrigin("http://localhost:5173")
//public class CabController {
//
//	@Autowired
//	private CabProxyController cabProxy;
//
//	@GetMapping("/getall")
//	public ResponseEntity<List<Cab>> getAllCabs() {
//		return cabProxy.getAllCabs();
//	}
//
//	@GetMapping("/getbyid/{id}")
//	public ResponseEntity<Optional<Cab>> getCabById(@PathVariable long id) {
//		return cabProxy.getCabById(id);
//	}
//
//
//	@PostMapping("/save")
//	public ResponseEntity<Cab> createCab(@RequestBody Cab Cab) {
//		return cabProxy.createCab(Cab);
//	}
//
//	@PostMapping("/bookCab/{id}/{departureTime}/{returntime}/{returntime}/{username}")
//	public ResponseEntity<CabBookingDetails> bookCab(@PathVariable long id,@PathVariable LocalDateTime departureTime,@PathVariable LocalDateTime returntime,@PathVariable String journeyType, @PathVariable String username) {
//		return cabProxy.bookCab(id, departureTime, returntime, journeyType, username);
//		}
//
//	@GetMapping("/getCabbookingdetailsbyid/{id}")
//	public ResponseEntity<CabBookingDetails> getCabBookingDetailsById(@PathVariable long id) {
//		return cabProxy.getCabBookingDetailsById(id);
//	}
//	
//	@GetMapping("/getCabbookingdetailsbyusername/{username}")
//	public ResponseEntity<List<CabBookingDetails>> getCabBookingDetailsByUsername(@PathVariable String username) {
//		return cabProxy.getCabBookingDetailsByUsername(username);	}
//		
//	@PutMapping("/update/{id}")
//	public ResponseEntity<Cab> updateCab(@PathVariable long id, @RequestBody Cab Cab) {
//		return cabProxy.updateCab(id, Cab);	}
//
//	@DeleteMapping("/delete/{id}")
//	public ResponseEntity<String> deleteCab(@PathVariable long id) {
//		return cabProxy.deleteCab(id;)	}
//
//	@PutMapping("/resetstatus/{id}")
//	public ResponseEntity<CabBookingDetails> resetstatus(@PathVariable long id) {
//		return cabProxy.resetstatus(id;)	}
//
//	@GetMapping("/getallcitynames")
//	public ResponseEntity<List<List<String>>> getAllCityNames() {
//		return cabProxy.getAllCityNames();	}
//
//	
//	@GetMapping("/paymentstatuschange/{bookingid}")
//	public ResponseEntity<CabBookingDetails> paymentstatuschange(@PathVariable long bookingid) {
//		return cabProxy.paymentstatuschange(bookingid);	}
//
//	@GetMapping("/getallavailableCabs/{from}/{to}/{departure}/{travellerClass}")
//	public ResponseEntity<List<Cab>> getAllAvailableCabs(@PathVariable String from, @PathVariable String to,
//			@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date departure, @PathVariable String travellerClass) {
//		return cabProxy.getAllAvailableCabs(from, to, departure, travellerClass);
//	}
//
//}
