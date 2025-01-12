package org.ashwin.projects.hotelmanagement.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.ashwin.projects.hotelmanagement.dto.HotelDto;
import org.ashwin.projects.hotelmanagement.dto.HotelSearchDto;
import org.ashwin.projects.hotelmanagement.entity.Hotel;
import org.ashwin.projects.hotelmanagement.entity.Inventory;
import org.ashwin.projects.hotelmanagement.entity.Room;
import org.ashwin.projects.hotelmanagement.repository.InventoryRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Service
@RequiredArgsConstructor
@Slf4j
public class InventoryServiceImpl implements InventoryService {

    private final InventoryRepository inventoryRepository;
    private final ModelMapper modelMapper;

    @Override
    public void initializeRoomForAYear(Room room) {
        LocalDate today = LocalDate.now();
        LocalDate endDate = today.plusYears(1);
        for(; !today.isAfter(endDate); today = today.plusDays(1)) {
            Inventory inventory = Inventory.builder()
                    .hotel(room.getHotel())
                    .date(today)
                    .price(room.getBasePrice())
                    .city(room.getHotel().getCity())
                    .room(room)
                    .bookedCount(0)
                    .surgeFactor(BigDecimal.ONE)
                    .totalCount(room.getTotalCount())
                    .isClosed(false)
                    .build();
            inventoryRepository.save(inventory);
        }
    }

    @Override
    public void deleteAllInventories(Room room) {
        inventoryRepository.deleteByRoom(room);
    }

    @Override
    public Page<HotelDto> searchHotels(HotelSearchDto hotelSearchDto) {
        log.info("Searching hotels for {}", hotelSearchDto);
        Pageable pageable = PageRequest.of(hotelSearchDto.getPage(), hotelSearchDto.getPageSize());
        long dateCount = ChronoUnit.DAYS.between(hotelSearchDto.getStartDate(), hotelSearchDto.getEndDate()) + 1;
        Page<Hotel> hotelPage = inventoryRepository.findHotelsWithAvailableInventory(
                hotelSearchDto.getCity(),
                hotelSearchDto.getStartDate(),
                hotelSearchDto.getEndDate(),
                hotelSearchDto.getRoomsCount(),
                dateCount,
                pageable
        );
        return hotelPage.map(hotel -> modelMapper.map(hotel, HotelDto.class));
    }
}
