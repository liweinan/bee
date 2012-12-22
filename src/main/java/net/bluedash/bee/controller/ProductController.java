package net.bluedash.bee.controller;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import java.io.Serializable;

/**
 * 12 22 2012
 *
 * @author <a href="mailto:l.weinan@gmail.com">Weinan Li</a>
 */
@Named("products")
@RequestScoped
public class ProductController implements Serializable {

    private String productName;

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    @PostConstruct
    public void init() {
        System.out.println("PRODUCT-NAME: " + productName);
    }
}
