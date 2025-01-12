package org.ashwin.projects.hotelmanagement.service;

import org.ashwin.projects.hotelmanagement.dto.HotelDto;
import org.ashwin.projects.hotelmanagement.dto.HotelSearchDto;
import org.ashwin.projects.hotelmanagement.entity.Hotel;
import org.ashwin.projects.hotelmanagement.entity.Room;
import org.springframework.data.domain.Page;

import java.time.LocalDate;

public interface InventoryService {

    void initializeRoomForAYear(Room room);

    void deleteAllInventories(Room room);

    Page<HotelDto> searchHotels(HotelSearchDto hotelSearchDto);

}
