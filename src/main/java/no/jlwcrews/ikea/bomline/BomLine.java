package no.jlwcrews.ikea.bomline;

import jakarta.persistence.*;
import no.jlwcrews.ikea.bom.Bom;
import no.jlwcrews.ikea.item.Item;

@Entity
public class BomLine {

    @Id
    @GeneratedValue(generator = "bomline_gen", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "bomline_gen", sequenceName = "bomline_seq", allocationSize = 1, initialValue = 1)
    @Column(name = "bomline_id")
    private long id;

    @ManyToOne
    @JoinColumn(name = "bom_id")
    private Bom bom;

    @ManyToOne
    @JoinColumn(name = "item_id")
    private Item item;

    private int quantity;

    public Bom getBom() {
        return bom;
    }

    public void setBom(Bom bom) {
        this.bom = bom;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public long getId() {
        return id;
    }
}
