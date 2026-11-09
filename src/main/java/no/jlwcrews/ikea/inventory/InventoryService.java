package no.jlwcrews.ikea.inventory;

import no.jlwcrews.ikea.bomline.BomLine;
import no.jlwcrews.ikea.exception.InsufficientQuantityException;
import no.jlwcrews.ikea.exception.ItemNotFoundException;
import no.jlwcrews.ikea.item.Item;
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

    public List<Inventory> getAllInventory() {
        return inventoryRepo.findAll();
    }

    public Inventory getInventoryById(long id) {
        return inventoryRepo.findById(id).orElse(null);
    }

    public Inventory getInventoryByItemId(long itemId) {
        return inventoryRepo.findByItemId(itemId).orElse(null);
    }

    @Transactional
    public void placeOrder(OrderRequestDTO request) {
        var item = itemService.getItemById(request.id());
        reserve(item, request.qty());
    }

    private void reserve(Item item, int needed) {
        var inv = inventoryRepo.findByItemId(item.getId()).orElse(null);
        int free = inv == null ? 0 : inv.getQuantityOnHand() - inv.getQuantityOnReserve();
        int taken = Math.min(free, needed);
        if (taken > 0) inv.setQuantityOnReserve(inv.getQuantityOnReserve() + taken);

        int remaining = needed - taken;
        if (remaining == 0) return;

        if (item.getBom() == null)
            throw new InsufficientQuantityException("Not enough " + item.getName() + ", short by " + remaining);

        for (BomLine line : item.getBom().getBomLines())
            reserve(line.getItem(), line.getQuantity() * remaining);
    }
}
