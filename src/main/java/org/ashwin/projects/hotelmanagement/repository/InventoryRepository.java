package org.ashwin.projects.hotelmanagement.repository;

import org.ashwin.projects.hotelmanagement.entity.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {
}
