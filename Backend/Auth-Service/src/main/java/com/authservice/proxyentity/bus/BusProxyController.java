package com.authservice.proxyentity.bus;

import java.util.List;
import java.util.Optional;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.authservice.proxyentity.bus.Bus;
import com.authservice.proxyentity.bus.BusSeat;

@FeignClient(name = "BUS-SERVICE", url = "http://localhost:8087/Bus")
public interface BusProxyController {
	@GetMapping("/getall")
	public ResponseEntity<List<Bus>> getAllBus();

	@GetMapping("/getbyid/{busId}")
	public ResponseEntity<Optional<Bus>> getBusBybusId(@PathVariable long busId);

	@PostMapping("/save")
	public ResponseEntity<Bus> createBus(@RequestBody Bus Bus);

	@PutMapping("/update/{busId}")
	public ResponseEntity<Bus> updateBus(@PathVariable long busId, @RequestBody Bus Bus);

	@DeleteMapping("/delete/{busId}")
	public ResponseEntity<String> deleteBus(@PathVariable long busId);

	@PostMapping("/addseat/{busId}")
	public ResponseEntity<Bus> addseat(@PathVariable long busId, @RequestBody BusSeat busSeat);

	@PutMapping("/updateseat/{busId}")
	public ResponseEntity<Bus> updateseat(@PathVariable long busId, @RequestBody BusSeat busSeat);

	@PutMapping("/resetstatus/{busId}")
	public ResponseEntity<Bus> resetstatus(@PathVariable long busId);
}
