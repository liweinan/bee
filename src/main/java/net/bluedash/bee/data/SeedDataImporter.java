package net.bluedash.bee.data;

import net.bluedash.bee.model.Product;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.UserTransaction;

/**
 * 12 11 2012
 *
 * @author <a href="mailto:l.weinan@gmail.com">Weinan Li</a>
 */
@Startup
@Singleton
public class SeedDataImporter {

//    @Inject
//    @Category("BEE-DATA-IMPORTER")
//    private Logger log;

    @Inject
    @MemberRepository
    private EntityManager em;

    @Inject
    private UserTransaction tx;

    @PostConstruct
    public void importData() {
        Product defaultProduct = new Product();
        defaultProduct.setName("DEFAULT-PRODUCT");
        defaultProduct.setDescription("Default Product");
        em.persist(defaultProduct);
    }
}
