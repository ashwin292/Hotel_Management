package org.ashwin.projects.hotelmanagement.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.ashwin.projects.hotelmanagement.dto.BookingDto;
import org.ashwin.projects.hotelmanagement.dto.BookingRequestDto;
import org.ashwin.projects.hotelmanagement.entity.*;
import org.ashwin.projects.hotelmanagement.entity.enums.BookingStatus;
import org.ashwin.projects.hotelmanagement.exception.ResourceNotFoundException;
import org.ashwin.projects.hotelmanagement.repository.BookingRepository;
import org.ashwin.projects.hotelmanagement.repository.HotelRepository;
import org.ashwin.projects.hotelmanagement.repository.InventoryRepository;
import org.ashwin.projects.hotelmanagement.repository.RoomRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepository;
    private final ModelMapper modelMapper;
    private final HotelRepository hotelRepository;
    private final RoomRepository roomRepository;
    private final InventoryRepository inventoryRepository;

    @Override
    @Transactional
    public BookingDto createBooking(BookingRequestDto bookingRequestDto) {
        log.info("Creating booking: {}", bookingRequestDto);
        Hotel hotel = hotelRepository.findById(bookingRequestDto.getHotelId()).orElseThrow(()->
                new ResourceNotFoundException("Hotel not found"));

        Room room = roomRepository.findById(bookingRequestDto.getRoomId()).orElseThrow(()->
                new ResourceNotFoundException("Room not found"));

        List<Inventory> inventoryList = inventoryRepository.findAndLockAvailableInventory(room.getId(),
                bookingRequestDto.getCheckInDate(), bookingRequestDto.getCheckOutDate(), bookingRequestDto.getRoomsCount());

        long daysCount = ChronoUnit.DAYS.between(bookingRequestDto.getCheckInDate(), bookingRequestDto.getCheckOutDate()) + 1;

        if (inventoryList.size() != daysCount) throw new IllegalStateException("Room is not available");

        //Reserve the room/update the booked count of inventory
        for (Inventory inventory : inventoryList) {
            inventory.setBookedCount(inventory.getBookedCount() + bookingRequestDto.getRoomsCount());
        }
        inventoryRepository.saveAll(inventoryList);

        //Create the booking

        //create dummy user to create booking
        //TODE: Remove dummy user
        User user = new User();
        user.setId(1L);
        Booking booking = Booking.builder()
                .bookingStatus(BookingStatus.RESERVED)
                .hotel(hotel)
                .room(room)
                .checkInDate(bookingRequestDto.getCheckInDate())
                .checkOutDate(bookingRequestDto.getCheckOutDate())
                .user(user)
                .roomsCount(bookingRequestDto.getRoomsCount())
                .amount(BigDecimal.TEN)
                .build();
        return modelMapper.map(bookingRepository.save(booking), BookingDto.class);
    }
}
