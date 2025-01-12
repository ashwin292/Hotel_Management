package org.ashwin.projects.hotelmanagement.service;

import org.ashwin.projects.hotelmanagement.dto.HotelDto;
import org.ashwin.projects.hotelmanagement.dto.HotelInfoDto;
import org.ashwin.projects.hotelmanagement.dto.RoomDto;

import java.util.List;

public interface HotelService {
    HotelDto createHotel(HotelDto hotelDto);
    HotelDto getHotelById(Long id);
    List<HotelDto> getAllHotels();
    HotelDto updateHotelById(Long id, HotelDto hotelDto);
    void deleteHotelById(Long id);
    void activateHotelById(Long id);

    HotelInfoDto getHotelInfoById(Long hotelId);
}
