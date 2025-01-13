package org.ashwin.projects.hotelmanagement.service;

import org.ashwin.projects.hotelmanagement.dto.BookingDto;
import org.ashwin.projects.hotelmanagement.dto.BookingRequestDto;

public interface BookingService {
    BookingDto createBooking(BookingRequestDto bookingRequestDto);
}
