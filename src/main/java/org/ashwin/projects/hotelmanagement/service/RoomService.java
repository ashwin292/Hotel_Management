package org.ashwin.projects.hotelmanagement.service;

import org.ashwin.projects.hotelmanagement.dto.RoomDto;

import java.util.List;

public interface RoomService {

    RoomDto createRoom(Long hotelId, RoomDto roomDto);
    RoomDto updateRoomById(Long id, RoomDto roomDto);
    void deleteRoomById(Long id);
    RoomDto getRoomById(Long id);
    List<RoomDto> getAllRoomsInHotel(Long hotelId);

}
