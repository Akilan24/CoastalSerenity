//package com.authservice.proxyentity.cab;
//
//import java.time.LocalDateTime;
//import java.util.Date;
//import java.util.List;
//import java.util.Optional;
//
//import org.springframework.cloud.openfeign.FeignClient;
//import org.springframework.format.annotation.DateTimeFormat;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.PutMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//
//import com.authservice.proxyentity.cab.Cab;
//import com.cabservice.entity.CabBookingDetails;
//
//@FeignClient(name = "CAB-SERVICE", url = "http://localhost:8085/Cab")
//public interface CabProxyController {
//
//	@GetMapping("/getall")
//	public ResponseEntity<List<Cab>> getAllCabs();
//
//	@GetMapping("/getbyid/{id}")
//	public ResponseEntity<Optional<Cab>> getCabById(@PathVariable long id);
//
//	@PostMapping("/save")
//	public ResponseEntity<Cab> createCab(@RequestBody Cab Cab);
//
//	@PostMapping("/bookCab/{id}/{departureTime}/{returntime}/{returntime}/{username}")
//	public ResponseEntity<CabBookingDetails> bookCab(@PathVariable long id, @PathVariable LocalDateTime departureTime,
//			@PathVariable LocalDateTime returntime, @PathVariable String journeyType, @PathVariable String username);
//
//	@GetMapping("/getCabbookingdetailsbyid/{id}")
//	public ResponseEntity<CabBookingDetails> getCabBookingDetailsById(@PathVariable long id);
//
//	@GetMapping("/getCabbookingdetailsbyusername/{username}")
//	public ResponseEntity<List<CabBookingDetails>> getCabBookingDetailsByUsername(@PathVariable String username);
//
//	@PutMapping("/update/{id}")
//	public ResponseEntity<Cab> updateCab(@PathVariable long id, @RequestBody Cab Cab);
//
//	@DeleteMapping("/delete/{id}")
//	public ResponseEntity<String> deleteCab(@PathVariable long id);
//
//	@PutMapping("/resetstatus/{id}")
//	public ResponseEntity<CabBookingDetails> resetstatus(@PathVariable long id);
//
//	@GetMapping("/getallcitynames")
//	public ResponseEntity<List<List<String>>> getAllCityNames();
//
//	@GetMapping("/paymentstatuschange/{bookingid}")
//	public ResponseEntity<CabBookingDetails> paymentstatuschange(@PathVariable long bookingid);
//
//	@GetMapping("/getallavailableCabs/{from}/{to}/{departure}/{travellerClass}")
//	public ResponseEntity<List<Cab>> getAllAvailableCabs(@PathVariable String from, @PathVariable String to,
//			@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date departure, @PathVariable String travellerClass);
//
//}
