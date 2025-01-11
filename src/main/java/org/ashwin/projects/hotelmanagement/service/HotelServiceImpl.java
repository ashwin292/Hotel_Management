package org.ashwin.projects.hotelmanagement.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.ashwin.projects.hotelmanagement.dto.HotelDto;
import org.ashwin.projects.hotelmanagement.entity.Hotel;
import org.ashwin.projects.hotelmanagement.exception.ResourceNotFoundException;
import org.ashwin.projects.hotelmanagement.repository.HotelRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class HotelServiceImpl implements HotelService {

    private final HotelRepository hotelRepository;
    private final ModelMapper modelMapper;

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
    public void deleteHotelById(Long id) {
        log.info("Deleting hotel: {}", id);
        if (!hotelRepository.existsById(id)) throw new ResourceNotFoundException("Hotel not found with Id: " + id);
        hotelRepository.deleteById(id);
        log.info("Hotel deleted: {}", id);
        //TODO: delete the future inventories for this hotel.
    }

}
