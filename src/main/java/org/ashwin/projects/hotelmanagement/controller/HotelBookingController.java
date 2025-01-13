package org.ashwin.projects.hotelmanagement.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.ashwin.projects.hotelmanagement.dto.BookingDto;
import org.ashwin.projects.hotelmanagement.dto.BookingRequestDto;
import org.ashwin.projects.hotelmanagement.service.BookingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/bookings")
public class HotelBookingController {

    private final BookingService bookingService;
    @PostMapping
    public ResponseEntity<BookingDto> createBooking(@RequestBody BookingRequestDto bookingRequestDto) {
        log.info("Creating a new booking request for hotel {}", bookingRequestDto.getHotelId());
        return new ResponseEntity<>(bookingService.createBooking(bookingRequestDto), HttpStatus.CREATED);
    }
}
