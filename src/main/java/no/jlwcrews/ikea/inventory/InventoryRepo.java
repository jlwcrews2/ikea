package no.jlwcrews.ikea.inventory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface InventoryRepo extends JpaRepository<Inventory, Long> {

    Optional<Inventory> findByItemId(long itemId);
}
