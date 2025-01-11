package org.ashwin.projects.hotelmanagement.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.ashwin.projects.hotelmanagement.dto.RoomDto;
import org.ashwin.projects.hotelmanagement.entity.Hotel;
import org.ashwin.projects.hotelmanagement.entity.Room;
import org.ashwin.projects.hotelmanagement.exception.ResourceNotFoundException;
import org.ashwin.projects.hotelmanagement.repository.HotelRepository;
import org.ashwin.projects.hotelmanagement.repository.RoomRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class RoomServiceImpl implements RoomService {

    private final RoomRepository roomRepository;
    private final ModelMapper modelMapper;
    private final HotelRepository hotelRepository;


    @Override
    public RoomDto createRoom(Long hotelId, RoomDto roomDto) {
        log.info("Creating room: {}", roomDto);
        Hotel hotel = hotelRepository.findById(hotelId).orElseThrow(() -> new ResourceNotFoundException("Hotel not found"));
        Room room = modelMapper.map(roomDto, Room.class);
        room.setHotel(hotel);

        //TODO: create inventory when room is created and if hotel is active.

        return modelMapper.map(roomRepository.save(room), RoomDto.class);
    }

    @Override
    public RoomDto updateRoomById(Long id, RoomDto roomDto) {
        log.info("Updating room: {}", roomDto);
        Room room = roomRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Room not found"));
        modelMapper.map(roomDto, room);
        room.setId(id);
        roomRepository.save(room);
        return modelMapper.map(room, RoomDto.class);
    }

    @Override
    public void deleteRoomById(Long id) {
        log.info("Deleting room: {}", id);
        if(!roomRepository.existsById(id)) throw new ResourceNotFoundException("Room not found");
        roomRepository.deleteById(id);

        //TODO: delete all future inventory for this room.
    }

    @Override
    public RoomDto getRoomById(Long id) {
        log.info("Getting room: {}", id);
        Room room = roomRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Room not found"));
        return modelMapper.map(room, RoomDto.class);
    }

    @Override
    public List<RoomDto> getAllRoomsInHotel(Long hotelId) {
        log.info("Getting all rooms in hotel: {}", hotelId);
        Hotel hotel = hotelRepository.findById(hotelId).orElseThrow(() -> new ResourceNotFoundException("Hotel not found"));
        return hotel.getRooms()
                .stream()
                .map(room -> modelMapper.map(room, RoomDto.class))
                .collect(Collectors.toList());
    }
}