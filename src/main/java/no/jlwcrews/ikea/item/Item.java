package no.jlwcrews.ikea.item;

import jakarta.persistence.*;
import no.jlwcrews.ikea.bom.Bom;

@Entity
public class Item {

    @Id
    @GeneratedValue(generator = "item_gen", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "item_gen", sequenceName = "item_seq", allocationSize = 1, initialValue = 1)
    @Column(name = "item_id")
    private long id;
    @Column(name = "item_name")
    private String name;
    @Column(name = "item_description")
    private String description;

    @OneToOne(mappedBy = "item")
    private Bom bom;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Bom getBom() {
        return bom;
    }

    public void setBom(Bom bom) {
        this.bom = bom;
    }

    public long getId() {
        return id;
    }
}
