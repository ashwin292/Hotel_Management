package org.ashwin.projects.hotelmanagement.repository;

import org.ashwin.projects.hotelmanagement.entity.Guest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GuestRepository extends JpaRepository<Guest, Long> {
}
