package org.ashwin.projects.hotelmanagement.dto;

import lombok.Data;
import org.ashwin.projects.hotelmanagement.entity.Hotel;
import org.ashwin.projects.hotelmanagement.entity.Room;
import org.ashwin.projects.hotelmanagement.entity.User;
import org.ashwin.projects.hotelmanagement.entity.enums.BookingStatus;

import java.time.LocalDate;
import java.util.Set;

@Data
public class BookingDto {
    private Long id;
    private Hotel hotel;
    private Room room;
    private User user;
    private Integer roomsCount;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
    private LocalDate createdAt;
    private LocalDate updatedAt;
    private BookingStatus bookingStatus;
    private Set<GuestDto> guests;
}
