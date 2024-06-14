package com.cabservice.entity;

import java.time.LocalDate;
import java.time.LocalTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookingRequest {

    private String from;
    private String to;
    private String journeyType;
    private String duration;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate departDate;
    @JsonFormat(pattern = "HH:mm")
    private LocalTime departTime;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate returnDate;
    @JsonFormat(pattern = "HH:mm")
    private LocalTime returnTime;
}
