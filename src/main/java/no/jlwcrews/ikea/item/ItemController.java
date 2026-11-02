package no.jlwcrews.ikea.item;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/item")
public class ItemController {

    private final ItemService itemService;
    private final ItemRepo itemRepo;

    public ItemController(ItemService itemService, ItemRepo itemRepo) {
        this.itemService = itemService;
        this.itemRepo = itemRepo;
    }

    @GetMapping("/{id}/requirements")
    public ResponseEntity<Map<String, Integer>> getRequirements(
            @PathVariable Long id,
            @RequestParam(defaultValue = "1") int qty
    ){
        var result = itemService.calculateRequirements(id, qty);
        return ResponseEntity.ok(result);
    }

    @GetMapping
    public ResponseEntity<List<ItemResponseDto>> getItems() {
        var itemList = itemRepo.findAll().stream().map(ItemMapper::toDto).toList();
        return ResponseEntity.ok(itemList);
    }
}
