package no.jlwcrews.ikea.bom;

import jakarta.persistence.*;
import no.jlwcrews.ikea.bomline.BomLine;
import no.jlwcrews.ikea.item.Item;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Bom {

    @Id
    @GeneratedValue(generator = "bom_gen", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "bom_gen", sequenceName = "bom_seq", allocationSize = 1, initialValue = 1)
    @Column(name = "bom_id")
    private long id;

    @OneToOne
    @JoinColumn(name = "item_id", nullable = false, unique = true)
    private Item item;

    @OneToMany(mappedBy = "bom")
    private List<BomLine> bomLines = new ArrayList<>();

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public List<BomLine> getBomLines() {
        return bomLines;
    }

    public void setBomLines(List<BomLine> bomLines) {
        this.bomLines = bomLines;
    }

    public long getId() {
        return id;
    }
}
