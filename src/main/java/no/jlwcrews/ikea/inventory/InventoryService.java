package no.jlwcrews.ikea.inventory;

import no.jlwcrews.ikea.exception.InsufficientQuantityException;
import no.jlwcrews.ikea.exception.ItemNotFoundException;
import no.jlwcrews.ikea.item.ItemService;
import no.jlwcrews.ikea.order.OrderRequestDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class InventoryService {

    private final ItemService itemService;

    private final InventoryRepo inventoryRepo;

    public InventoryService(ItemService itemService, InventoryRepo inventoryRepo) {
        this.itemService = itemService;
        this.inventoryRepo = inventoryRepo;
    }

    public List<Inventory> getAllInventory(){
        return inventoryRepo.findAll();
    }

    public Inventory getInventoryById(long id){
        return inventoryRepo.findById(id).orElse(null);
    }

    @Transactional
    public void placeOrder(OrderRequestDTO request){
        var requirements = itemService.calculateRequirements(request.id(), request.qty());
        requirements.forEach((itemId, quantity) -> {
            var inventoryItem = inventoryRepo.findById(itemId).orElseThrow(() -> new ItemNotFoundException("Item no exist"));
            if(inventoryItem.getQuantityOnHand() < quantity) throw new InsufficientQuantityException("Insufficient quantity on hand of " + itemId);
            inventoryItem.setQuantityOnReserve(quantity);
            inventoryRepo.save(inventoryItem);
        });
    }
}
