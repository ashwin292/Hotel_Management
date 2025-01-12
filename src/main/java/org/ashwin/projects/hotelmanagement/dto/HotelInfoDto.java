package org.ashwin.projects.hotelmanagement.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class HotelInfoDto {
    HotelDto hotel;
    List<RoomDto> rooms;
}
