package org.ashwin.projects.hotelmanagement.dto;

import lombok.Data;
import org.ashwin.projects.hotelmanagement.entity.User;
import org.ashwin.projects.hotelmanagement.entity.enums.Gender;

@Data
public class GuestDto {
    private Long id;
    private User user;
    private String name;
    private Gender gender;
    private Integer age;
}
