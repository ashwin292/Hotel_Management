package org.ashwin.projects.hotelmanagement.dto;

import lombok.Data;
import org.ashwin.projects.hotelmanagement.entity.ContactInfo;

@Data
public class HotelDto {
    private long id;
    private String name;
    private String city;
    private String[] photos;
    private String[] amenities;
    private ContactInfo contactInfo;
    private boolean isActive;
}
