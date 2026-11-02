package no.jlwcrews.ikea.item;

import no.jlwcrews.ikea.bomline.BomLine;
import no.jlwcrews.ikea.exception.ItemNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class ItemService {

    private final ItemRepo itemRepo;

    public ItemService(ItemRepo itemRepo) {
        this.itemRepo = itemRepo;
    }

    public Map<String, Integer> calculateRequirements(Long itemId, int qty){
        Map<String, Integer> requirements = new HashMap<>();
        Item item = itemRepo.findById(itemId).orElseThrow(() -> new ItemNotFoundException("Item not found"));
        calculateAllRequirementsForItem(item, qty, requirements);
        return requirements;
    }

    private void calculateAllRequirementsForItem(Item item, int qty, Map<String, Integer> requirements){
        if (item.getBom() == null) {
            requirements.merge(item.getName(), qty, Integer::sum);
            return;
        }
        for( BomLine bomLine: item.getBom().getBomLines() ) {
            int requiredQty = bomLine.getQuantity() * qty;
            calculateAllRequirementsForItem(bomLine.getItem(), requiredQty, requirements);
        }
    }
}
