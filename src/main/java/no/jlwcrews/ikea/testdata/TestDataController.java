package no.jlwcrews.ikea.testdata;

import no.jlwcrews.ikea.bom.Bom;
import no.jlwcrews.ikea.bom.BomRepo;
import no.jlwcrews.ikea.bomline.BomLine;
import no.jlwcrews.ikea.bomline.BomLineRepo;
import no.jlwcrews.ikea.item.Item;
import no.jlwcrews.ikea.item.ItemMapper;
import no.jlwcrews.ikea.item.ItemRepo;
import no.jlwcrews.ikea.item.ItemResponseDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/testdata")
public class TestDataController {

    private final BomRepo bomRepo;
    private final ItemRepo itemRepo;
    private final BomLineRepo bomLineRepo;

    public TestDataController(BomRepo bomRepo, ItemRepo itemRepo, BomLineRepo bomLineRepo) {
        this.bomRepo = bomRepo;
        this.itemRepo = itemRepo;
        this.bomLineRepo = bomLineRepo;
    }

    @GetMapping
    public List<ItemResponseDto> getItems() {
        return itemRepo.findAll().stream().map(ItemMapper::toDto).toList();
    }

    @PostMapping
    public void createTestData() {
        // 1. Create items
        Item screw = createItem("Screw", "Standard wood screw");
        Item woodenLeg = createItem("Wooden Leg", "Wooden furniture leg");
        Item tabletop = createItem("Tabletop", "Wooden desk tabletop");
        Item legAssembly = createItem("Leg Assembly", "Complete desk leg assembly");
        Item desk = createItem("Writing Desk", "Finished wooden writing desk");

        // 2. Create BOM for leg assembly
        Bom legAssemblyBom = createBom(legAssembly);
        createBomLine(legAssemblyBom, woodenLeg, 4);
        createBomLine(legAssemblyBom, screw, 4);

        // 3. Create BOM for desk
        Bom deskBom = createBom(desk);
        createBomLine(deskBom, tabletop, 1);
        createBomLine(deskBom, legAssembly, 4);
        createBomLine(deskBom, screw, 20);
    }

    private Item createItem(String name, String description) {
        Item item = new Item();
        item.setName(name);
        item.setDescription(description);
        return itemRepo.save(item);
    }

    private Bom createBom(Item item) {
        Bom bom = new Bom();
        bom.setItem(item);
        item.setBom(bom);
        return bomRepo.save(bom);
    }

    private void createBomLine(Bom bom, Item item, int quantity) {
        BomLine line = new BomLine();
        line.setBom(bom);
        line.setItem(item);
        line.setQuantity(quantity);
        bomLineRepo.save(line);
    }
}
