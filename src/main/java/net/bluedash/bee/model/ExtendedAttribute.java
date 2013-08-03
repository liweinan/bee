package net.bluedash.bee.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * 12 21 2012
 *
 * @author <a href="mailto:l.weinan@gmail.com">Weinan Li</a>
 */
@Deprecated
@Entity
@Table(name="bee_extended_attribute")
public class ExtendedAttribute {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    @NotNull
    @Column(nullable = false)
    private String name;

    @NotNull
    @ManyToOne
    private Task product;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Task getProduct() {
        return product;
    }

    public void setProduct(Task product) {
        this.product = product;
    }
}
