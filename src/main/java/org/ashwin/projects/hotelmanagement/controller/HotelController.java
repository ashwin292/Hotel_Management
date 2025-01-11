package org.ashwin.projects.hotelmanagement.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.ashwin.projects.hotelmanagement.dto.HotelDto;
import org.ashwin.projects.hotelmanagement.service.HotelService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/hotels")
@RequiredArgsConstructor
@Slf4j
public class HotelController {
    private final HotelService hotelService;

    @PostMapping
    public ResponseEntity<HotelDto> createHotel(@RequestBody HotelDto hotel) {
        log.info("attempting to create new hotel: {}", hotel);
        HotelDto createdHotel = hotelService.createHotel(hotel);
        return new ResponseEntity<>(createdHotel, HttpStatus.CREATED);
    }

    @GetMapping("/{hotelId}")
    public ResponseEntity<HotelDto> getHotelById(@PathVariable Long hotelId) {
        log.info("attempting to get hotel by id: {}", hotelId);
        HotelDto hotel = hotelService.getHotelById(hotelId);
        System.out.println(hotel.toString());
        return new ResponseEntity<>(hotel, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<HotelDto>> getAllHotels() {
        log.info("attempting to get all hotels");
        return new ResponseEntity<>(hotelService.getAllHotels(), HttpStatus.OK);
    }

    @PutMapping("/{hotelId}")
    public ResponseEntity<HotelDto> updateHotelById(@PathVariable Long hotelId, @RequestBody HotelDto hotel) {
        log.info("attempting to update hotel by id: {}", hotelId);
        HotelDto updatedHotel = hotelService.updateHotelById(hotelId, hotel);
        return new ResponseEntity<>(updatedHotel, HttpStatus.OK);
    }

    @DeleteMapping("/{hotelId}")
    public ResponseEntity<Void> deleteHotelById(@PathVariable Long hotelId) {
        log.info("attempting to delete hotel by id: {}", hotelId);
        hotelService.deleteHotelById(hotelId);
        return ResponseEntity.noContent().build();
    }

}
