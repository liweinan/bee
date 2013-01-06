package net.bluedash.bee.controller;

import net.bluedash.bee.data.MemberRepository;
import net.bluedash.bee.model.Product;
import net.bluedash.bee.model.Product_;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * 12 12 2012
 *
 * @author <a href="mailto:l.weinan@gmail.com">Weinan Li</a>
 */
@RequestScoped
public class DataProducer {

    @Inject
    @MemberRepository
    private EntityManager em;

    private List<Product> productsForMenu;

    @Produces
    @Named
    public List<Product> getProductsForMenu() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Product> criteria = cb.createQuery(Product.class);
        Root<Product> productRoot = criteria.from(Product.class);
        criteria.select(productRoot).orderBy(cb.asc(productRoot.get(Product_.name)));
        productsForMenu = em.createQuery(criteria).getResultList();
        return productsForMenu;
    }

}
