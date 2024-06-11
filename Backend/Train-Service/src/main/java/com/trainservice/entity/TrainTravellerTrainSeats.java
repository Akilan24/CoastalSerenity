package com.trainservice.entity;

import java.util.List;

import com.trainservice.externalclass.Traveller;

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
