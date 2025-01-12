package org.ashwin.projects.hotelmanagement.repository;

import org.ashwin.projects.hotelmanagement.entity.Hotel;
import org.ashwin.projects.hotelmanagement.entity.Inventory;
import org.ashwin.projects.hotelmanagement.entity.Room;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {

    void deleteByRoom(Room room);

    @Query("""
            select distinct i.hotel from Inventory i
                        where i.city = :city
                                    and i.date between :startDate and :endDate
                                                and i.isClosed = false
                                                    and (i.totalCount-i.bookedCount) >= :roomsCount
                                                            group by i.hotel, i.room
                                                                        having count(i.date) = :dateCount
            """)
    Page<Hotel> findHotelsWithAvailableInventory(
            @Param("city") String city,
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate,
            @Param("roomsCount") Integer roomsCount,
            @Param("dateCount") Long dateCount,
            Pageable pageable
    );
}
