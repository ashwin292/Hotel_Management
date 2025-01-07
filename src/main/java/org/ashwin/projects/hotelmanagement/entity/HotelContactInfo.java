package org.ashwin.projects.hotelmanagement.entity;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Embeddable
public class HotelContactInfo {
    private String address;
    private String phone;
    private String email;
    private String location;
}
