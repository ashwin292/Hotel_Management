package org.ashwin.projects.hotelmanagement.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.ashwin.projects.hotelmanagement.entity.HotelContactInfo;

@Data
public class HotelDto {
    private long id;
    private String name;
    private String city;
    private String[] photos;
    private String[] amenities;
    private HotelContactInfo contactInfo;
    private boolean isActive;
}
