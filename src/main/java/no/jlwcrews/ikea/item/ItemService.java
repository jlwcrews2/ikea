package no.jlwcrews.ikea.item;

import no.jlwcrews.ikea.bomline.BomLine;
import no.jlwcrews.ikea.exception.InsufficientQuantityException;
import no.jlwcrews.ikea.exception.ItemNotFoundException;
import no.jlwcrews.ikea.inventory.InventoryService;
import no.jlwcrews.ikea.order.OrderRequestDTO;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ItemService {

    private final ItemRepo itemRepo;

    public ItemService(ItemRepo itemRepo) {
        this.itemRepo = itemRepo;
    }

    public Map<Long, Integer> calculateRequirements(Long itemId, int qty){
        Map<Long, Integer> requirements = new HashMap<>();
        Item item = itemRepo.findById(itemId).orElseThrow(() -> new ItemNotFoundException("Item not found"));
        calculateAllRequirementsForItem(item, qty, requirements);
        return requirements;
    }

    public List<Item> getAllItems(){
        return itemRepo.findAll();
    }

    private void calculateAllRequirementsForItem(Item item, int qty, Map<Long, Integer> requirements){
        if (item.getBom() == null) {
            requirements.merge(item.getId(), qty, Integer::sum);
            return;
        }
        for( BomLine bomLine: item.getBom().getBomLines() ) {
            int requiredQty = bomLine.getQuantity() * qty;
            calculateAllRequirementsForItem(bomLine.getItem(), requiredQty, requirements);
        }
    }


}
