package com.authservice.proxyentity.train;

import java.util.List;

import com.authservice.entity.Traveller;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TrainTravellerTrainSeats {

	List<Traveller> travellers;
	List<TrainSeats> trainSeats;
}
