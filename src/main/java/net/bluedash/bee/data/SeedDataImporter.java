package net.bluedash.bee.data;

import net.bluedash.bee.model.*;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;
import javax.persistence.EntityManager;

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

    @PostConstruct
    public void importData() {

        User defaultUser = new User();
        defaultUser.setUsername("DEFAULT-USER");
        em.persist(defaultUser);

        Product defaultProduct = new Product();
        defaultProduct.setName("DEFAULT-PRODUCT");
        defaultProduct.setDescription("Default Product");

        Label defaultLabel = new Label();
        defaultLabel.setName("DEFAULT-LABEL");
        defaultLabel.setTimeTracked(false);
        defaultLabel.setProduct(defaultProduct);
        defaultProduct.getLabels().add(defaultLabel);

        Tag defaultTag = new Tag();
        defaultTag.setName("DEFAULT-TAG");
        defaultTag.setProduct(defaultProduct);
        defaultProduct.getTags().add(defaultTag);

        Tag defaultTag2 = new Tag();
        defaultTag2.setName("DEFAULT-TAG-2");
        defaultTag2.setProduct(defaultProduct);
        defaultProduct.getTags().add(defaultTag2);

        Task defaultTask = new Task();
        defaultTask.setProduct(defaultProduct);
        defaultTask.setAssignee(defaultUser);
        defaultTask.setName("DEFAULT-TASK");
        defaultTask.getTags().add(defaultTag);
        defaultTask.getTags().add(defaultTag2);
        defaultTask.setLabel(defaultLabel);

        defaultProduct.getTasks().add(defaultTask);
        em.persist(defaultProduct);

        // Add global setting
        DisplayPosition globalDisplayPosition = new DisplayPosition();
        globalDisplayPosition.setPos(DisplayPosition.POS_ENTITY | DisplayPosition.POS_LIST);
        globalDisplayPosition.setGlobal(true);
        em.persist(globalDisplayPosition);
    }
}
