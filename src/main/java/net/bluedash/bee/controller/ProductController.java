package net.bluedash.bee.controller;

import net.bluedash.bee.data.MemberRepository;
import net.bluedash.bee.model.Product;
import net.bluedash.bee.model.Product_;
import net.bluedash.bee.model.Task;
import net.bluedash.bee.model.Task_;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 12 22 2012
 *
 * @author <a href="mailto:l.weinan@gmail.com">Weinan Li</a>
 */
@Named("products")
@RequestScoped
public class ProductController implements Serializable {

    @Inject
    @MemberRepository
    private EntityManager em;

    private String productName;

    private List<Task> tasks = new ArrayList<Task>();

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void generateTaskListOfProduct() {
        System.out.println("PRODUCT-NAME: " + productName);
        if (productName == null) return;

        CriteriaBuilder builder = em.getCriteriaBuilder();

        // get target product
        CriteriaQuery<Product> productCriteria = builder.createQuery(Product.class);
        Root<Product> productRoot = productCriteria.from(Product.class);
        productCriteria.select(productRoot).where(builder.equal(productRoot.get(Product_.name), productName));
        Product product;
        try {
            product = em.createQuery(productCriteria).getSingleResult();
        } catch (javax.persistence.NoResultException e) {
            return;
        }

        // get tasks of product
        CriteriaQuery<Task> criteria = builder.createQuery(Task.class);
        Root<Task> root = criteria.from(Task.class);
        criteria.select(root).where(builder.equal(root.get(Task_.product), product));

        tasks = em.createQuery(criteria).getResultList();
    }
}
