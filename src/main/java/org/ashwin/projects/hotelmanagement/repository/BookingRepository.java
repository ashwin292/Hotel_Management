package org.ashwin.projects.hotelmanagement.repository;

import org.ashwin.projects.hotelmanagement.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<Booking, Long> {
}
