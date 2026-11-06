package no.jlwcrews.ikea.item;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/item")
public class ItemController {

    private final ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping("/{id}/requirements")
    public ResponseEntity<Map<Long, Integer>> getRequirements(
            @PathVariable Long id,
            @RequestParam(defaultValue = "1") int qty
    ){
        var result = itemService.calculateRequirements(id, qty);
        return ResponseEntity.ok(result);
    }

    @GetMapping
    public ResponseEntity<List<ItemResponseDto>> getItems() {
        var itemList = itemService.getAllItems().stream().map(ItemMapper::toDto).toList();
        return ResponseEntity.ok(itemList);
    }
}
