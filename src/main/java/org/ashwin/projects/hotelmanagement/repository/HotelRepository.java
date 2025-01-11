package org.ashwin.projects.hotelmanagement.repository;

import org.ashwin.projects.hotelmanagement.entity.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface HotelRepository extends JpaRepository<Hotel, Long> {
}
