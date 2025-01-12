package org.ashwin.projects.hotelmanagement.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.ashwin.projects.hotelmanagement.dto.HotelDto;
import org.ashwin.projects.hotelmanagement.dto.HotelInfoDto;
import org.ashwin.projects.hotelmanagement.dto.RoomDto;
import org.ashwin.projects.hotelmanagement.entity.Hotel;
import org.ashwin.projects.hotelmanagement.entity.Room;
import org.ashwin.projects.hotelmanagement.exception.ResourceNotFoundException;
import org.ashwin.projects.hotelmanagement.repository.HotelRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class HotelServiceImpl implements HotelService {

    private final HotelRepository hotelRepository;
    private final ModelMapper modelMapper;
    private final InventoryService inventoryService;

    @Override
    public HotelDto createHotel(HotelDto hotelDto) {
        log.info("Creating hotel: {}", hotelDto);
        Hotel hotel = modelMapper.map(hotelDto, Hotel.class);
        hotel.setIsActive(false);  // initially the hotel would be inactive
        hotel = hotelRepository.save(hotel);
        log.info("Hotel created: {}", hotel);
        return modelMapper.map(hotel, HotelDto.class);
    }

    @Override
    public HotelDto getHotelById(Long id) {
        log.info("Getting hotel: {}", id);
        Hotel hotel = hotelRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Hotel not found with Id: " + id));
        return modelMapper.map(hotel, HotelDto.class);
    }

    @Override
    public List<HotelDto> getAllHotels() {
        List<Hotel> hotels = hotelRepository.findAll();
        List<HotelDto> hotelDtos = new ArrayList<>();
        for (Hotel hotel : hotels) {
            hotelDtos.add(modelMapper.map(hotel, HotelDto.class));
        }
        return hotelDtos;
    }

    @Override
    public HotelDto updateHotelById(Long id, HotelDto hotelDto) {
        log.info("Updating hotel: {}", hotelDto);
        Hotel hotel = hotelRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Hotel not found with Id: " + id));
        modelMapper.map(hotelDto, hotel);
        hotel.setId(id);
        hotel = hotelRepository.save(hotel);
        return modelMapper.map(hotel, HotelDto.class);
    }

    @Override
    @Transactional
    public void deleteHotelById(Long id) {
        log.info("Deleting hotel: {}", id);
        Hotel hotel = hotelRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Hotel not found with Id: " + id));
//        for (Room room : hotel.getRooms()) {
//            inventoryService.deleteAllInventories(room);
//        }
        hotelRepository.deleteById(id);
        log.info("Hotel deleted: {}", id);
    }

    @Override
    @Transactional
    public void activateHotelById(Long id) {
        log.info("Activating hotel: {}", id);
        Hotel hotel = hotelRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Hotel not found with Id: " + id));
        hotel.setIsActive(true);
        hotelRepository.save(hotel);
        log.info("Hotel activated: {}", id);

        // do it once
        for(Room room : hotel.getRooms()) {
            inventoryService.initializeRoomForAYear(room);
        }
    }

    @Override
    public HotelInfoDto getHotelInfoById(Long hotelId) {
        log.info("Getting hotel info: {}", hotelId);
        Hotel hotel = hotelRepository.findById(hotelId).orElseThrow(() -> new ResourceNotFoundException("Hotel not found with Id: " + hotelId));
        List<RoomDto> roomDtos = hotel.getRooms().stream().map(room -> modelMapper.map(room, RoomDto.class)).collect(Collectors.toList());
//        for (Room room : hotel.getRooms()) {
//            roomDtos.add(modelMapper.map(room, RoomDto.class));
//        }
        return new HotelInfoDto(modelMapper.map(hotel, HotelDto.class), roomDtos);
    }
}
