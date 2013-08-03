package net.bluedash.bee.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * 12 21 2012
 *
 * @author <a href="mailto:l.weinan@gmail.com">Weinan Li</a>
 */
@Entity
@Table(name="bee_settings")
//@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
//@DiscriminatorColumn(name = "col_type", discriminatorType = DiscriminatorType.STRING)
//@DiscriminatorValue("se")
public class Setting {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    @NotNull
    private Boolean global;

    @ManyToOne
    private Task product;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Task getProduct() {
        return product;
    }

    public void setProduct(Task product) {
        this.product = product;
    }

    public Boolean getGlobal() {
        return global;
    }

    public void setGlobal(Boolean global) {
        this.global = global;
    }
}
