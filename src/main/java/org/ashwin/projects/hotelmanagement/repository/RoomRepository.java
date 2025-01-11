package org.ashwin.projects.hotelmanagement.repository;

import org.ashwin.projects.hotelmanagement.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository extends JpaRepository<Room, Long> {
}
