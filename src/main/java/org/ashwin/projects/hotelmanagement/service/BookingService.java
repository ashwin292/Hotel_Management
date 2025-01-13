package org.ashwin.projects.hotelmanagement.service;

import org.ashwin.projects.hotelmanagement.dto.BookingDto;
import org.ashwin.projects.hotelmanagement.dto.BookingRequestDto;
import org.ashwin.projects.hotelmanagement.dto.GuestDto;

import java.util.List;

public interface BookingService {
    BookingDto createBooking(BookingRequestDto bookingRequestDto);

    BookingDto addGuests(Long bookingId, List<GuestDto> guestDtos);
}
