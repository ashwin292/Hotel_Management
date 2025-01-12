package org.ashwin.projects.hotelmanagement.controller;

import lombok.RequiredArgsConstructor;
import org.ashwin.projects.hotelmanagement.dto.HotelDto;
import org.ashwin.projects.hotelmanagement.dto.HotelInfoDto;
import org.ashwin.projects.hotelmanagement.dto.HotelSearchDto;
import org.ashwin.projects.hotelmanagement.service.HotelService;
import org.ashwin.projects.hotelmanagement.service.InventoryService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/hotels")
@RequiredArgsConstructor
public class HotelBrowseController {

    private final InventoryService inventoryService;
    private final HotelService hotelService;

    @GetMapping("/search")
    public ResponseEntity<Page<HotelDto>> search(@RequestBody HotelSearchDto hotelSearchDto) {
        Page<HotelDto> page = inventoryService.searchHotels(hotelSearchDto);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{hotelId}/info")
    public ResponseEntity<HotelInfoDto> getHotelInfo(@PathVariable Long hotelId) {
        return new ResponseEntity<>(hotelService.getHotelInfoById(hotelId), HttpStatus.OK);
    }
}
