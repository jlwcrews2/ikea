package no.jlwcrews.ikea.inventory;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import no.jlwcrews.ikea.item.Item;

@Entity
public class Inventory {

    @Id
    @GeneratedValue(generator = "inventory_gen", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "inventory_gen", sequenceName = "inventory_seq", allocationSize = 1, initialValue = 1)
    @Column(name = "inventory_id")
    private Long id;

    @OneToOne
    @JoinColumn(name = "inventory_item_id")
    @JsonIgnoreProperties("bom")
    private Item item;

    @Column(name = "inventory_qty_on_hand")
    private int quantityOnHand;
    @Column(name = "inventory_qty_on_reserve")
    private int quantityOnReserve;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public int getQuantityOnHand() {
        return quantityOnHand;
    }

    public void setQuantityOnHand(int quantityOnHand) {
        this.quantityOnHand = quantityOnHand;
    }

    public int getQuantityOnReserve() {
        return quantityOnReserve;
    }

    public void setQuantityOnReserve(int quantityOnReserve) {
        this.quantityOnReserve = quantityOnReserve;
    }
}
